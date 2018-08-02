package ru.panmin.gtspro.ui.tradepointinfo.me;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class TradePointInfoMePresenter extends ToolbarPresenter<TradePointInfoMeMvpView> {

    private final DataManager dataManager;


    @Inject
    TradePointInfoMePresenter(DataManager dataManager) {
        this.dataManager = dataManager;

    }

    @Override
    protected void dispose() {
    }

    public void getTradePoint(String tradePointId) {
        getMvpView().setTradePoint(dataManager.getTradePointById(tradePointId));
    }

}