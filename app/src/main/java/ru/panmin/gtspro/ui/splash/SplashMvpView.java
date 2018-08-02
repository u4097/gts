package ru.panmin.gtspro.ui.splash;

import ru.panmin.gtspro.ui.base.MvpView;

interface SplashMvpView extends MvpView {

    void showNoInternetDialog();

    void openAuthActivity();

    void openMainActivity();

}