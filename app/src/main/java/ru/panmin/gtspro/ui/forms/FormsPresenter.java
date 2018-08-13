package ru.panmin.gtspro.ui.forms;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class FormsPresenter extends ToolbarPresenter<FormsMvpView> {

    private final DataManager dataManager;

    @Inject
    FormsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getPromo(String promoId) {
        getMvpView().setPromo(dataManager.getPromoById(promoId));
    }

}