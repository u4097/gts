package ru.panmin.gtspro.ui.blocks;

import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

public interface BlockMvpView extends ToolbarMvpView {
    void initViews(String fullName, String role);
    void setTradePoint(TradePoint tradePointById);
    void initFilter();
    void openLoginActivity();
    void selectNewSortType(String sortType);
    void setBlockTitle(String title);
    void initBlockData(BlockType.Type blockType);
}