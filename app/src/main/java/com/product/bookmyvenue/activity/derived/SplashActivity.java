package com.product.bookmyvenue.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.product.bookmyvenue.R;
import com.product.bookmyvenue.User;
import com.product.bookmyvenue.activity.base.BaseActivity;
import com.product.bookmyvenue.global.Constants;
import com.product.bookmyvenue.handler.derived.PhoneAuthHandler;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Activity class for handling splash screen and {@link FirebaseAuth} phone signIn.<br/>
 * 1. Check SMS Permissions.<br/>
 * 2. On Login click, call initPhoneAuth() for OTP request.<br/>
 * 3. On OTP receiving, call signInWithPhoneAuthCredential() to verify the OTP and login.<br/>
 * 4. Update Name and mobile in FireStore database and navigate to dashboard.
 */
public class SplashActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = "SplashActivity";

    /**
     * Class private data member(s).
     */
    private EditText mEtName = null;
    private EditText mEtMobile = null;
    private Button mBtnVerify = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        PhoneAuthHandler.mActivity = this;

        mEtName = findViewById(R.id.et_name);
        mEtMobile = findViewById(R.id.et_mobile);
        mBtnVerify = findViewById(R.id.btn_verify);
        mBtnVerify.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {

        // Verify button click event handling.
        if (view.getId() == R.id.btn_verify) {

            String strMobile = "+91" + mEtMobile.getText().toString().trim();

            if (validateFields()) {
                new PhoneAuthHandler().initPhoneAuth(strMobile);
            }
        }
    }


    /**
     * Method to update profile and navigate to dashboard screen.
     */
    public void phoneAuthResponse(boolean done, String message) {

        if (!done) {

            // If not done, then return.
            MDToast.makeText(this, message, MDToast.LENGTH_SHORT, MDToast.TYPE_WARNING).show();
            return;
        }

        // Proceed for profile registration and navigate to dashboard.
        MDToast.makeText(this, message, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();

        String strMobile = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        String strName = mEtName.getText().toString().trim();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String strCurrentDateTime = dateFormatter.format(new Date());

        User user = new User();
        user.setName(strName);
        user.setMobile(strMobile);
        user.setCreatedOn(strCurrentDateTime);
        user.setLastLogin(strCurrentDateTime);
        user.setRate(0.0f);
        user.setRateCount(0);
        user.setVisit(0);
        user.setEmail("");
        user.setAddress("");
        user.setPic("");
        user.setPosts(0);
        user.setGeo("");

        FirebaseFirestore.getInstance().collection("activeUsers").document(strMobile).set(user);

        startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
        finish();
    }

    /**
     * Method to check for the input field(s).
     * Also, raise the appropriate error message.
     *
     * @return true = valid, false = validation-failed.
     */
    private boolean validateFields() {

        String strName = mEtName.getText().toString().trim();
        String strMobile = mEtMobile.getText().toString().trim();

        boolean isValid = true;

        // Name - field validation.
        if (!Pattern.compile(Constants.REGEX_NAME).matcher(strName).matches()) {

            isValid = false;
            mEtName.setError(getString(R.string.enter_your_name));

        } else {

            mEtName.setError(null);
        }

        // Mobile - field validation.
        if (!Pattern.compile(Constants.REGEX_MOBILE).matcher(strMobile).matches()) {

            isValid = false;
            mEtMobile.setError(getString(R.string.enter_your_name));

        } else {

            mEtMobile.setError(null);
        }

        return isValid;
    }

}
