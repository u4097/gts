package ru.panmin.gtspro.utils;

import android.content.Context;
import android.support.annotation.StringRes;

import com.afollestad.materialdialogs.MaterialDialog;

import ru.panmin.gtspro.R;

public class DialogUtils {

    private DialogUtils() {
    }

    public static MaterialDialog createProgressDialog(Context context) {
        return createProgressDialog(context, R.string.dialog_progress_default);
    }

    public static MaterialDialog createProgressDialog(Context context, @StringRes int messageResource) {
        return createProgressDialog(context, context.getResources().getString(messageResource));
    }

    public static MaterialDialog createProgressDialog(Context context, String message) {
        return new MaterialDialog.Builder(context)
                .content(message)
                .cancelable(false)
                .progress(true, 0)
                .build();
    }

    public static MaterialDialog createSplashErrorDialog(Context context, String content,
                                                         MaterialDialog.SingleButtonCallback negativeCallback,
                                                         MaterialDialog.SingleButtonCallback positiveCallback) {
        return new MaterialDialog.Builder(context)
                .title(R.string.error)
                .content(content)
                .positiveText(R.string.repeat)
                .onPositive(positiveCallback)
                .negativeText(R.string.exit)
                .onNegative(negativeCallback)
                .cancelable(false)
                .build();
    }

}