package ru.panmin.gtspro.ui.blocks;

import ru.panmin.gtspro.ui.blocks.model.PromoModel;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface BlockMvpView extends ToolbarMvpView {
    void showInfo(PromoModel promoModel);
}