package ru.panmin.gtspro.ui.base;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;

class SyncPresenter extends BasePresenter<MvpView> {

    private final DataManager dataManager;

    @Inject
    SyncPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    void checkNeedSync() {
        if (dataManager.isAuth() && dataManager.isNeedUpdateDB()) {
            getMvpView().startSync();
        }
    }

}