package com.fendonus.fake_coder.firebaseauth.utilities;

import android.content.Context;
import android.util.Log;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.Date;

public class ToastUtility {

    public static void showInfo(Context context, String message) {
        TastyToast.makeText(context, message,
                TastyToast.LENGTH_LONG, TastyToast.INFO);
    }

    public static void showWarningInfo(Context context, String message) {
        TastyToast.makeText(context, message,
                TastyToast.LENGTH_LONG, TastyToast.WARNING);
    }

    public static void showErrorInfo(Context context, String message) {
        TastyToast.makeText(context, message,
                TastyToast.LENGTH_LONG, TastyToast.ERROR);
    }

    public static void showConfusingInfo(Context context, String message) {
        TastyToast.makeText(context, message,
                TastyToast.LENGTH_LONG, TastyToast.CONFUSING);
    }

    public static void showLog(String tag, String message) {
        Date date = new Date();
        Log.d(tag, date.toString() +" "+ message);
    }
}
