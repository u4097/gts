package ru.panmin.gtspro.ui.claiminfo.sv;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class ClaimInfoSvPresenter extends ToolbarPresenter<ClaimInfoSvMvpView> {

    private final DataManager dataManager;

    @Inject
    ClaimInfoSvPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    public void getClaim(String claimId) {
        getMvpView().setClaim(dataManager.getClaimById(claimId));

        if (getMvpView().getClaim() != null) {
            getMvpView().setClient(dataManager.getClientById(getMvpView().getClaim().getClientId()));
        }
    }

}