package com.product.bookmyvenue.activity.derived;

import android.os.Bundle;

import com.product.bookmyvenue.R;
import com.product.bookmyvenue.activity.base.BaseActivity;

/**
 * Activity class to handle dashboard UI.
 */
public class DashboardActivity extends BaseActivity {

    public static final String TAG = "DashboardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

    }

}
