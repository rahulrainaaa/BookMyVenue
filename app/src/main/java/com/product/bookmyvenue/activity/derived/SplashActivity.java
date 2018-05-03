package com.product.bookmyvenue.activity.derived;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.product.bookmyvenue.R;
import com.product.bookmyvenue.activity.base.BaseActivity;
import com.product.bookmyvenue.handler.derived.UserProfileHandler;

import java.util.concurrent.TimeUnit;

/**
 * Activity class for handling splash screen and {@link FirebaseAuth} phone signIn.<br/>
 * 1. Check SMS Permissions.<br/>
 * 2. On Login click, call initPhoneAuth() for OTP request.<br/>
 * 3. On OTP receiving, call signInWithPhoneAuthCredential() to verify the OTP and login.<br/>
 * 4. Call checkUserProfile() to get user profile (if it is existing user) and navigate to dashboard. If profile not found then (means: new user) navigate to create profile screen.
 */
public class SplashActivity extends BaseActivity {

    public static final String TAG = "SplashActivity";


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        askForSMSPermission();
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {

            // Need to login.
            Toast.makeText(this, "Need to login.", Toast.LENGTH_SHORT).show();

        } else {

            // Already login.
            Toast.makeText(this, "Already logged in.", Toast.LENGTH_SHORT).show();
            UserProfileHandler userProfileHandler = new UserProfileHandler();
            userProfileHandler.fetchUser(this, FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {

            initPhoneAuth("+917387802229");
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * Method to enable the phone auth code.
     *
     * @param strMobile mobile number with country code as String.
     */
    private void initPhoneAuth(String strMobile) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(strMobile, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
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

        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(SplashActivity.this, "SignIn Successful", Toast.LENGTH_SHORT).show();
                    checkUserProfile();

                } else {

                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                        Toast.makeText(SplashActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(SplashActivity.this, "Failed Exception", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    /**
     * Method to check if the user profile exists then navigate to dashboard.
     * If new user login, then navigate to edit profile screen.
     */
    private void checkUserProfile() {


    }

    /**
     * Method to prompt for SMS permission from user.
     */
    private void askForSMSPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{android.Manifest.permission.RECEIVE_SMS}, 1);
            }
        }
    }
}
