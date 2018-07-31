package ru.panmin.gtspro.ui.tradepointinfo.sv;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class TradePointInfoSvPresenter extends ToolbarPresenter<TradePointInfoSvMvpView> {

    private final DataManager dataManager;

    @Inject
    TradePointInfoSvPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

}