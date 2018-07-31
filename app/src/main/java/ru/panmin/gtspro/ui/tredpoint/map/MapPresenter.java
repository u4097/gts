package ru.panmin.gtspro.ui.tredpoint.map;

import android.annotation.SuppressLint;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.models.responses.AddressProgramResponse;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;
import ru.panmin.gtspro.utils.RxEventBus;
import ru.panmin.gtspro.utils.RxUtils;

public class MapPresenter extends ProgressPresenter<MapMvpView> {

    private final DataManager dataManager;
    private final RxEventBus rxEventBus;

    @SuppressLint("CheckResult")
    @Inject
    MapPresenter(DataManager dataManager, RxEventBus rxEventBus) {
        this.dataManager = dataManager;
        this.rxEventBus = rxEventBus;

        RxUtils.dispose(disposable);
        rxEventBus.filteredObservable(AddressProgramResponse.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        addressProgramResponse -> {
                            Log.d("aevedv", "devedvde");
                        }
                );
    }

    @Override
    protected void dispose() {
    }

}