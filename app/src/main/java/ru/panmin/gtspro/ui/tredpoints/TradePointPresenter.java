package ru.panmin.gtspro.ui.tredpoints;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;

public class TradePointPresenter extends ProgressPresenter<TradePointMvpView> {

    private final DataManager dataManager;

    @Inject
    TradePointPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(TradePointMvpView mvpView) {
        super.attachView(mvpView);
        dataManager.createWithConnect();
    }

    public void initViews() {
        getMvpView().initViews(dataManager.getFullName(), dataManager.getRole());
    }

    public void exit() {
        dataManager.clear();
        getMvpView().openLoginActivity();
        getMvpView().finishActivity();
    }

}