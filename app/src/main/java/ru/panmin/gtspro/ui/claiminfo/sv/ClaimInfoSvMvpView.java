package ru.panmin.gtspro.ui.claiminfo.sv;

import android.support.annotation.StringRes;
import android.widget.TextView;

import ru.panmin.gtspro.data.models.Claim;
import ru.panmin.gtspro.data.models.Client;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface ClaimInfoSvMvpView extends ToolbarMvpView {

    void setClaim(Claim claim);

    void setValue(TextView tv, String text, @StringRes Integer label);

    Claim getClaim();

    void setClient(Client client);
}