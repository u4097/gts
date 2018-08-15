package ru.panmin.gtspro.ui.promoinfo.sv;

import android.support.annotation.StringRes;
import android.widget.TextView;

import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface PromoInfoSvMvpView extends ToolbarMvpView {

    void setPromo(Promo promo);

}