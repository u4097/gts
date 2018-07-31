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

    @Override
    protected void dispose() {
    }

}