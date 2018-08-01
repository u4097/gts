package ru.panmin.gtspro.ui.tredpoint;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;
import ru.panmin.gtspro.utils.Constants;
import ru.panmin.gtspro.utils.RxEventBus;
import ru.panmin.gtspro.utils.RxUtils;

public class TradePointPresenter extends ProgressPresenter<TradePointMvpView> {

    private final DataManager dataManager;
    private final RxEventBus rxEventBus;

    @Inject
    TradePointPresenter(DataManager dataManager, RxEventBus rxEventBus) {
        this.dataManager = dataManager;
        this.rxEventBus = rxEventBus;
    }

    @Override
    protected void dispose() {
    }

    void getAddressProgram() {
        getMvpView().setStateLoading();
        RxUtils.dispose(disposable);
        disposable = dataManager.addressProgram()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        addressProgramResponse -> {
                            rxEventBus.post(addressProgramResponse);
                            getMvpView().setStateData();
                        },
                        throwable -> {
                            parseError(throwable);
                        }
                );
    }

    public void initViews() {
        getMvpView().initViews(dataManager.getFullName(), dataManager.getRole());
    }

    public void exit() {
        dataManager.clear();
        getMvpView().openLoginActivity();
        getMvpView().finishActivity();
    }

}