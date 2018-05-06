package com.product.bookmyvenue.handler.derived;

import android.util.Log;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.product.bookmyvenue.activity.derived.SplashActivity;

import java.util.concurrent.TimeUnit;

/**
 * Handler class to handle FireBase phone Auth and send the response.
 * Do Phone Auth SignIn and then send status response to SplashActivity.
 */
public class PhoneAuthHandler {

    private static final String TAG = "PhoneAuthHandler";
    public static SplashActivity mActivity = null;

    /**
     * Method to enable the phone auth code.
     *
     * @param strMobile mobile number with country code as String.
     */
    public void initPhoneAuth(String strMobile) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(strMobile, 60, TimeUnit.SECONDS, mActivity, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                Log.d(TAG, "Mobile verification successful.");
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Log.d(TAG, "Mobile verification failed.");
            }
        });
    }

    /**
     * Method to verify the received OTP for mobile number login.
     *
     * @param phoneAuthCredential reference.
     */
    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {

        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(mActivity, task -> {

            if (task.isSuccessful()) {

                mActivity.phoneAuthResponse(true, "SignIn Successful");

            } else {

                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                    mActivity.phoneAuthResponse(true, task.getException().getMessage());

                } else {

                    mActivity.phoneAuthResponse(true, "SignIn Failed\nTry Again!");
                }
            }
        });
    }
}
