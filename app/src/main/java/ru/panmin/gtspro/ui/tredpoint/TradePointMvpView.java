package ru.panmin.gtspro.ui.tredpoint;

import ru.panmin.gtspro.ui.progress.ProgressMvpView;


interface TradePointMvpView extends ProgressMvpView {

    void initViews(String fullName, String role);

    void openLoginActivity();

}