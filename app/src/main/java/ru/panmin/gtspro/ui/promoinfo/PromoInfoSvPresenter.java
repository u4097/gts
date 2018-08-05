package ru.panmin.gtspro.ui.promoinfo;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.blocks.viewmodel.PromoViewModelStub;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class PromoInfoSvPresenter extends ToolbarPresenter<PromoInfoSvMvpView> {

    private final DataManager dataManager;
    PromoViewModelStub promViewModelStub = new PromoViewModelStub();

    @Inject
    PromoInfoSvPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

    public void getPromo(String promoId) {
           promViewModelStub.loadData("0");
//        getMvpView().setPromo(dataManager.getPromoById(promoId));
        getMvpView().setPromo(promViewModelStub.getData().get(Integer.parseInt(promoId)));
    }

}