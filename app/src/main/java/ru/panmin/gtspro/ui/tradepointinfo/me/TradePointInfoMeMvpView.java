package ru.panmin.gtspro.ui.tradepointinfo.me;

import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface TradePointInfoMeMvpView extends ToolbarMvpView {

    void setTradePoint(TradePoint tradePoint);

}