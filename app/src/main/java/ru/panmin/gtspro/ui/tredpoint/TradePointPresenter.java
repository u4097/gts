package ru.panmin.gtspro.ui.tredpoint;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

public class TradePointPresenter extends ToolbarPresenter<TradePointMvpView> {

    private final DataManager dataManager;

    @Inject
    TradePointPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {

    }
}