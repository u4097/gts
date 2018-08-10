package ru.panmin.gtspro.ui.blocks;

import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface BlockMvpView extends ToolbarMvpView {

    void setTradePoint(TradePoint tradePointById);

    void openLoginActivity();

}