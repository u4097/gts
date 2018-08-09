package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_sku;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;

public class ChoiceSkuListPresenter extends ProgressPresenter<ChoiceSkuListMvpView> {

    private final DataManager dataManager;

    @Inject
    ChoiceSkuListPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {

    }
}
