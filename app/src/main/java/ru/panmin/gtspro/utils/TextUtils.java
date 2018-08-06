package ru.panmin.gtspro.utils;

public class TextUtils {

    private TextUtils() {
    }

    public static boolean isEmpty(CharSequence str) {
        if (str == null) {
            return true;
        } else {
            if (str instanceof String) {
                return android.text.TextUtils.isEmpty(((String) str).trim());
            } else {
                return android.text.TextUtils.isEmpty(str);
            }
        }
    }

    public static boolean equals(CharSequence a, CharSequence b) {
        return android.text.TextUtils.equals(a, b);
    }

}