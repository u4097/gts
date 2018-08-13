package ru.panmin.gtspro.ui.tredpoints.me;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;

public class MeTradePointPresenter extends ProgressPresenter<MeTradePointMvpView> {

    private final DataManager dataManager;

    @Inject
    MeTradePointPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void afterInitViews() {
        getMvpView().setTradePoint(dataManager.getTradePoints(), dataManager.getSortType());
        getMvpView().setStateData();
    }

}