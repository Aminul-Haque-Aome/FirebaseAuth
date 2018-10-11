package com.fendonus.fake_coder.firebaseauth;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(mSplashTask, SPLASH_TIME_OUT);
    }

    private Runnable mSplashTask = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashScreenActivity.this, DashboardActivity.class));
            finish();
        }
    };
}
