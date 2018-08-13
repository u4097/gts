package ru.panmin.gtspro.ui.tradepointinfo.sv.merchandiser;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class MerchandiserPresenter extends ToolbarPresenter<MerchandiserMvpView> {

    private final DataManager dataManager;

    @Inject
    MerchandiserPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getMerchandiser(String merchandiserName) {
        getMvpView().setMerchandiser(dataManager.getMerchandiserByName(merchandiserName));
    }

}