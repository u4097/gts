package ru.panmin.gtspro.ui.claiminfo.me;

import android.support.annotation.StringRes;
import android.widget.TextView;

import ru.panmin.gtspro.data.models.Claim;
import ru.panmin.gtspro.data.models.Client;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface ClaimInfoMeMvpView extends ToolbarMvpView {

    void setClient(Client client);
    void setValue(TextView tv, String text, @StringRes Integer label);
    Claim getClaim();
    void setClaim(Claim claim);
}