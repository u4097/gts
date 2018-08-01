package ru.panmin.gtspro.ui.tredpoint.supervision_trade_point;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.models.responses.AddressProgramResponse;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;
import ru.panmin.gtspro.utils.RxEventBus;
import ru.panmin.gtspro.utils.RxUtils;

public class SwTradePointPresenter extends ProgressPresenter<SwTradePointMvpView> {

    private final DataManager dataManager;
    private final RxEventBus rxEventBus;

    @Inject
    SwTradePointPresenter(DataManager dataManager, RxEventBus rxEventBus) {
        this.dataManager = dataManager;
        this.rxEventBus = rxEventBus;
    }

    @SuppressLint("CheckResult")
    @Override
    public void attachView(SwTradePointMvpView mvpView) {
        super.attachView(mvpView);
        RxUtils.dispose(disposable);
        rxEventBus.filteredObservable(AddressProgramResponse.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        addressProgramResponse -> {
                            getMvpView().setTradePoint(addressProgramResponse.getTradePoints());
                            getMvpView().setStateData();
                        },
                        throwable -> {
                            parseError(throwable);
                        }
                );
    }

    @Override
    protected void dispose() {
    }

}