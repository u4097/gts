package ru.panmin.gtspro.ui.tredpoint.merchandise_trade_point;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.models.responses.AddressProgramResponse;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;
import ru.panmin.gtspro.utils.RxEventBus;
import ru.panmin.gtspro.utils.RxUtils;

public class MeTradePointPresenter extends ProgressPresenter<MeTradePointMvpView> {

    private final DataManager dataManager;

    @Inject
    MeTradePointPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

    public void afterInitViews() {
        getMvpView().setTradePoint(dataManager.getTradePoints());
        getMvpView().setStateData();
    }

}