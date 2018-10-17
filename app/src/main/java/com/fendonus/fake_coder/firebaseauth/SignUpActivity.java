package com.fendonus.fake_coder.firebaseauth;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import com.fendonus.fake_coder.firebaseauth.config.Constant;
import com.fendonus.fake_coder.firebaseauth.models.UserInfoModel;
import com.fendonus.fake_coder.firebaseauth.utilities.ProgressUtility;
import com.fendonus.fake_coder.firebaseauth.utilities.StringUtility;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mNidEditText;
    private EditText mAddressEditText;
    private EditText mPhoneEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mConfirmPasswordEditText;

    private ProgressUtility mProgressUtility;

    private CheckBox mAcceptTermsAndConditionCheckBox;
    private Button mSignUpButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mUserInfoDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViewAndVariable();

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString();
                String nid = mNidEditText.getText().toString();
                String address = mAddressEditText.getText().toString();
                String phone = mPhoneEditText.getText().toString();
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String retypePassword = mConfirmPasswordEditText.getText().toString();

                if (!StringUtility.isNullOrEmpty(name, nid, address, phone, email, password, retypePassword)
                        && mAcceptTermsAndConditionCheckBox.isChecked()) {
                    attemptToSignUp(name, nid, address, phone, email, password, retypePassword);
                } else {
                    TastyToast.makeText(SignUpActivity.this,
                            "Please Fill All Fields", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        finish();
    }

    private void initViewAndVariable() {
        mAuth = FirebaseAuth.getInstance();
        mUserInfoDatabaseRef = FirebaseDatabase.getInstance().getReference();

        mNameEditText = findViewById(R.id.activity_user_info_name_edit_text);
        mNidEditText = findViewById(R.id.activity_user_info_nid_edit_text);
        mAddressEditText = findViewById(R.id.activity_user_info_address_edit_text);
        mPhoneEditText = findViewById(R.id.activity_user_info_phone_edit_text);
        mEmailEditText = findViewById(R.id.activity_user_info_mail_edit_text);
        mPasswordEditText = findViewById(R.id.activity_user_info_password_edit_text);
        mConfirmPasswordEditText = findViewById(R.id.activity_user_info_retype_password_edit_text);

        mAcceptTermsAndConditionCheckBox = findViewById(R.id.activity_user_info_accept_check_box);
        mSignUpButton = findViewById(R.id.activity_user_info_sign_up_button);

        mProgressUtility = new ProgressUtility(this);
    }

    private void attemptToSignUp(final String name, final String nid,
                                 final String address, final String phone,
                                 final String email, final String password,
                                 String reTypePassword) {
        if (password.equals(reTypePassword)) {
            mProgressUtility.showProgressDialog("Please Wait ...");

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    mProgressUtility.hideProgressDialog();

                    if (task.isSuccessful()) {
                        final String userUniqueId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        UserInfoModel userInfoModel = new UserInfoModel(name, userUniqueId, nid, address, email, password, phone);
                        saveUserInfoToFireBaseDatabase(userUniqueId, userInfoModel);

                        goToDashBoardActivity();
                    } else {

                        try {
                            throw task.getException();
                        } catch (FirebaseAuthWeakPasswordException weakPassword) {
                            TastyToast.makeText(SignUpActivity.this, "Weak Password",
                                    TastyToast.LENGTH_LONG, TastyToast.ERROR);
                        } catch (FirebaseAuthInvalidCredentialsException malformedEmail) {
                            TastyToast.makeText(SignUpActivity.this, "Mal-Formed Email",
                                    TastyToast.LENGTH_LONG, TastyToast.ERROR);
                        } catch (FirebaseAuthUserCollisionException existEmail) {
                            TastyToast.makeText(SignUpActivity.this, "This Email already exist",
                                    TastyToast.LENGTH_LONG, TastyToast.ERROR);
                        } catch (Exception exception) {
                            TastyToast.makeText(SignUpActivity.this, "" + exception.getMessage().toString(),
                                    TastyToast.LENGTH_LONG, TastyToast.INFO);
                        }
                    }
                }

            });

        } else {
            TastyToast.makeText(SignUpActivity.this,
                    "Please Enter Same Password", TastyToast.LENGTH_SHORT, TastyToast.CONFUSING);
        }
    }

    private void goToDashBoardActivity() {
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    private void saveUserInfoToFireBaseDatabase(String uniqueId, UserInfoModel userInfo) {
        mUserInfoDatabaseRef.child(Constant.USER_TABLE_NAME)
                .child(uniqueId)
                .setValue(userInfo)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this,
                                    "Error : " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
