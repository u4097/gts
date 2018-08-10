package ru.panmin.gtspro.ui.claiminfo.sv;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;
import timber.log.Timber;

class ClaimInfoSvPresenter extends ToolbarPresenter<ClaimInfoSvMvpView> {

    private final DataManager dataManager;

    @Inject
    ClaimInfoSvPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

    public void getClaim(String claimId) {
        // TODO: 08/08/2018 Not implemented
        Timber.d("Method getClaim not implemented.");
//        getMvpView().setClaim(dataManager.getClaimById(claimId));
        getMvpView().setClaim(null);
    }

}