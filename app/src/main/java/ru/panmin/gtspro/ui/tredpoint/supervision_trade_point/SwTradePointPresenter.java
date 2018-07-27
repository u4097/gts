package ru.panmin.gtspro.ui.tredpoint.supervision_trade_point;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

public class SwTradePointPresenter extends ToolbarPresenter<SwTradePointMvpView> {

    private final DataManager dataManager;

    @Inject
    SwTradePointPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {

    }
}
