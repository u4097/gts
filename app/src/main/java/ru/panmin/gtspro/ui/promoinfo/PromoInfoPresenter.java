package ru.panmin.gtspro.ui.promoinfo;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class PromoInfoPresenter extends ToolbarPresenter<PromoInfoMvpView> {

    private final DataManager dataManager;

    @Inject
    PromoInfoPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

    public void getPromo(String promoId) {
        getMvpView().setPromo(dataManager.getPromoById(promoId));
    }

}