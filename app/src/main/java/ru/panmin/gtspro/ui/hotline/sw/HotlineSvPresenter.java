package ru.panmin.gtspro.ui.hotline.sw;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;

public class HotlineSvPresenter extends ProgressPresenter<HotlineSvMvpView> {

    private final DataManager dataManager;


    @Inject
    HotlineSvPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getClient(String tradePointId) {
        getMvpView().setClient(dataManager.getTradePointById(tradePointId));
    }
}
