package ru.panmin.gtspro.ui.promoinfo;

import android.support.annotation.StringRes;
import android.widget.TextView;

import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface PromoInfoMvpView extends ToolbarMvpView {
    void setValue(TextView tv, String text, @StringRes Integer label);
}