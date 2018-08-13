package ru.panmin.gtspro.ui.tredpoints.sv;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;

public class SwTradePointPresenter extends ProgressPresenter<SwTradePointMvpView> {

    private final DataManager dataManager;

    @Inject
    SwTradePointPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void afterInitViews() {
        getMvpView().setTradePoint(dataManager.getTradePoints(), dataManager.getSortType());
        getMvpView().setStateData();
    }

}