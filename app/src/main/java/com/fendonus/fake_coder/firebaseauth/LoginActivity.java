package com.fendonus.fake_coder.firebaseauth;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.annotation.NonNull;

import android.os.Bundle;
import android.content.Intent;

import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdsmdg.tastytoast.TastyToast;

import com.fendonus.fake_coder.firebaseauth.utilities.ProgressUtility;
import com.fendonus.fake_coder.firebaseauth.utilities.StringUtility;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout mLayerLayout;
    private TextView mSignUpTextView;
    private Button mSignInButton;

    private ProgressUtility mProgressUtility;

    private TextInputLayout mEmailTextInputLayout;
    private TextInputLayout mPasswordTextInputLayout;

    private AppCompatEditText mEmailEditText;
    private AppCompatEditText mPasswordEditText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initAll();
        setUpListener();

        mSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                if(!StringUtility.isNullOrEmpty(email, password)) {
                    attemptToLogIn(email, password);
                } else {
                    if (StringUtility.isNullOrEmpty(email) && StringUtility.isNullOrEmpty(password)) {
                        TastyToast.makeText(LoginActivity.this, "Please Fill Both Field",
                                TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (StringUtility.isNullOrEmpty(email) && !StringUtility.isNullOrEmpty(password)) {
                        TastyToast.makeText(LoginActivity.this, "Please Insert Email",
                                TastyToast.LENGTH_LONG, TastyToast.CONFUSING);
                    } else {
                        TastyToast.makeText(LoginActivity.this, "Please Insert Password",
                                TastyToast.LENGTH_LONG, TastyToast.CONFUSING);
                    }
                }

            }
        });
    }

    private void setUpListener() {
        mLayerLayout.setOnClickListener(null);

        focusChangedListener();
        textChangeListener();
    }

    private void textChangeListener() {
        mEmailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEmailEditText.getText().toString().isEmpty()) {
                    mEmailTextInputLayout.setErrorEnabled(true);
                    mEmailTextInputLayout.setError("Enter Your Email");
                } else {
                    mEmailTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mPasswordEditText.getText().toString().isEmpty()) {
                    mPasswordTextInputLayout.setErrorEnabled(true);
                    mPasswordTextInputLayout.setError("Enter Your Password");
                } else {
                    mPasswordTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void focusChangedListener() {
        mEmailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (mEmailEditText.getText().toString().isEmpty()) {
                    mEmailTextInputLayout.setErrorEnabled(true);
                    mEmailTextInputLayout.setError("Enter Your Email");
                } else {
                    mEmailTextInputLayout.setErrorEnabled(false);
                }
            }
        });

        mPasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (mPasswordEditText.getText().toString().isEmpty()) {
                    mPasswordTextInputLayout.setErrorEnabled(true);
                    mPasswordTextInputLayout.setError("Enter Your Password");
                } else {
                    mPasswordTextInputLayout.setErrorEnabled(false);
                }
            }
        });
    }

    private void initAll() {
        mLayerLayout = findViewById(R.id.activity_login_layout);
        mSignUpTextView = findViewById(R.id.activity_login_sign_up_text_view);
        mSignInButton = findViewById(R.id.activity_login_sign_in_button);

        mEmailTextInputLayout = findViewById(R.id.activity_login_email_input_layout);
        mPasswordTextInputLayout = findViewById(R.id.activity_login_password_input_layout);

        mEmailEditText = findViewById(R.id.activity_login_email_edit_text);
        mPasswordEditText = findViewById(R.id.activity_login_password_edit_text);

        mAuth = FirebaseAuth.getInstance();
        mProgressUtility = new ProgressUtility(this);
    }

    private void attemptToLogIn(String email, String password) {
        mProgressUtility.showProgressDialog("Please Wait ...");

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressUtility.hideProgressDialog();

                if(task.isSuccessful()) {
                    goToDashboardActivity();
                } else {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException invalidEmail) {
                        TastyToast.makeText(LoginActivity.this, "Invalid Email",
                                TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    } catch (FirebaseAuthInvalidCredentialsException wrongPassword) {
                        TastyToast.makeText(LoginActivity.this, "Wrong Password",
                                TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    } catch (Exception exception) {
                        TastyToast.makeText(LoginActivity.this, ""+ exception.getMessage().toString(),
                                TastyToast.LENGTH_LONG, TastyToast.INFO);
                    }
                }
            }
        });
    }

    private void goToDashboardActivity() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        finish();
    }

}