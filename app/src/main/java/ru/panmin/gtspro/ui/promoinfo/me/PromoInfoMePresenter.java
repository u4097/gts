package ru.panmin.gtspro.ui.promoinfo.me;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class PromoInfoMePresenter extends ToolbarPresenter<PromoInfoMeMvpView> {

    private final DataManager dataManager;

    @Inject
    PromoInfoMePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

    public void getPromo(String promoId) {
        getMvpView().setPromo(dataManager.getPromoById(promoId));
    }

}