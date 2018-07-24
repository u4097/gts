package ru.panmin.gtspro.utils;

import android.content.res.Resources;
import android.text.TextUtils;

public class OtherUtils {

    private OtherUtils() {
    }

    public static int dpToPx(double dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static boolean isInternetError(Throwable e) {
        return !TextUtils.isEmpty(e.getMessage())
                && e.getMessage().contains("Unable to resolve host")
                && e.getMessage().contains("No address associated with hostname");
    }

}