package ru.panmin.gtspro.ui.claiminfo.me;

import android.support.annotation.StringRes;
import android.widget.TextView;

import ru.panmin.gtspro.data.models.Claim;
import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface ClaimInfoMeMvpView extends ToolbarMvpView {

    void setClaim(Claim claim);

    void setValue(TextView tv, String text, @StringRes Integer label);

}