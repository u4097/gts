package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;

public class ChoiseSkuPresenter extends ProgressPresenter<ChoiseSkuMvpView> {

    private final DataManager dataManager;

    @Inject
    ChoiseSkuPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {

    }
}
