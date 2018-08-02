package ru.panmin.gtspro.ui.tradepointinfo.sv;

import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface TradePointInfoSvMvpView extends ToolbarMvpView {

    void setTradePoint(TradePoint tradePoint);

}