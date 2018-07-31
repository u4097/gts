package ru.panmin.gtspro.ui.tredpoint;

import ru.panmin.gtspro.ui.progress.ProgressMvpView;


interface TradePointMvpView extends ProgressMvpView {

    void initNavigationDrawer(String fullName, String role);


    void setRole(boolean b);

    void openLoginActivity();

}
