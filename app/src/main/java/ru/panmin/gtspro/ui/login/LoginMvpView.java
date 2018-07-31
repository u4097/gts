package ru.panmin.gtspro.ui.login;

import ru.panmin.gtspro.ui.base.MvpView;

interface LoginMvpView extends MvpView {
    void showLoginValidError();
    void showPasswordValidError();
    void openMainActivity();
}