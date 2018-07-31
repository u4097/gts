package ru.panmin.gtspro.ui.base;

import android.widget.EditText;

public interface MvpView {

    void showProgressDialog();

    void hideProgressDialog();

    void showKeyboard(EditText editText);

    void hideKeyboard();

    void finishActivity();

    void showError(String error);

    void showUnknownServerError();

    void showUnknownError();

}