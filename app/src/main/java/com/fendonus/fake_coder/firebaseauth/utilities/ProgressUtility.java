package com.fendonus.fake_coder.firebaseauth.utilities;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressUtility {

    private Context mContext;
    private ProgressDialog mProgressDialog;

    public ProgressUtility(Context mContext) {
        this.mContext = mContext;
        mProgressDialog = new ProgressDialog(mContext);
    }

    public void showProgressDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(message);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
