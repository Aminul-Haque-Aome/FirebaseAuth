package com.fendonus.fake_coder.firebaseauth.utilities;

public class StringUtility {

    public static boolean isNullOrEmpty(String... fields) {

        for (String field : fields) {
            if (field == null || field.length() == 0) {
                return true;
            }
        }

        return false;
    }
}
