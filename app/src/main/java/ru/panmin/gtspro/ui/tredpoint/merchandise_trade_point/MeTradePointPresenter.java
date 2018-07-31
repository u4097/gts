package ru.panmin.gtspro.ui.tredpoint.merchandise_trade_point;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;

public class MeTradePointPresenter
        extends ProgressPresenter<MeTradePointMvpView> {

    private final DataManager dataManager;

    @Inject
    MeTradePointPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {

    }
}
