package ru.panmin.gtspro.ui.hotline.sw.messege_sw.fragment_selected_sku;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;

public class SelectedSkuListPresenter
        extends ProgressPresenter<SelectedSkuListMvpView> {

    private final DataManager dataManager;

    @Inject
    SelectedSkuListPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {

    }
}
