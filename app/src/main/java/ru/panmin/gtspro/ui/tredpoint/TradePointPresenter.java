package ru.panmin.gtspro.ui.tredpoint;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;
import ru.panmin.gtspro.utils.RxUtils;
import timber.log.Timber;

public class TradePointPresenter extends ProgressPresenter<TradePointMvpView> {

    private final DataManager dataManager;

    @Inject
    TradePointPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

    void getAddressProgram() {
        RxUtils.dispose(disposable);
        disposable = dataManager.addressProgram()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        addressProgramResponse -> {
                            Log.d("kxggxv", "exhpehx");
                        },
                        throwable -> {
                            parseError(throwable);
                        }
                );
    }

    public void initNavigationDrawer() {
        getMvpView().initNavigationDrawer(dataManager.getFullName(), dataManager.getRole());
    }

    public void exit() {
        dataManager.clear();
        getMvpView().openLoginActivity();
        getMvpView().finishActivity();
    }

}