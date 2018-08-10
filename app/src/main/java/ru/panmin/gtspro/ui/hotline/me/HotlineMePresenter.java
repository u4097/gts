package ru.panmin.gtspro.ui.hotline.me;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;

public class HotlineMePresenter extends ProgressPresenter<HotlineMeMvpView> {

    private final DataManager dataManager;


    @Inject
    HotlineMePresenter(DataManager dataManager) {
        this.dataManager = dataManager;

    }

    @Override
    protected void dispose() {

    }

    public void getClient(String id) {
        getMvpView().setClient(dataManager.getTradePointById(id));
    }

}
