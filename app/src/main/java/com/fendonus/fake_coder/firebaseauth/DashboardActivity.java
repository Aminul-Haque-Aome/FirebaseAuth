package com.fendonus.fake_coder.firebaseauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener mUserStatusListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();
        checkIfUserIsLoggedIn();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mUserStatusListener);
    }

    private void checkIfUserIsLoggedIn() {
        mUserStatusListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    sendToLoginActivity();
                }
            }
        };
    }

    private void sendToLoginActivity() {
        startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
        finish();
    }
}
