package ru.panmin.gtspro.ui.promoinfo.sv;

import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface PromoInfoSvMvpView extends ToolbarMvpView {

    void setPromo(Promo promo);

}