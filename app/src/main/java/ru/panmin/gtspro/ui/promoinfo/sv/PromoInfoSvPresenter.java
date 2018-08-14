package ru.panmin.gtspro.ui.promoinfo.sv;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class PromoInfoSvPresenter extends ToolbarPresenter<PromoInfoSvMvpView> {

    private final DataManager dataManager;

    @Inject
    PromoInfoSvPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getPromo(String promoId) {
        getMvpView().setPromo(dataManager.getPromoById(promoId));
    }

}