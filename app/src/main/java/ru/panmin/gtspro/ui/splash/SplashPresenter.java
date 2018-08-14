package ru.panmin.gtspro.ui.splash;

import android.os.CountDownTimer;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.base.BasePresenter;

class SplashPresenter extends BasePresenter<SplashMvpView> {

    private static final int LAUNCH_TIME = 1500;
    private static final int TIMER_STEP = LAUNCH_TIME;

    private final DataManager dataManager;

    private CountDownTimer timer;

    private boolean isLoading = false;

    @Inject
    SplashPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    void init(boolean isOnline) {
        if (dataManager.isAuth() && dataManager.isNeedUpdateDB()) {
            dataManager.clearDataBase();
            if (isOnline) {
                isLoading = true;
                Calendar calendar = Calendar.getInstance();
                disposables.add(dataManager.addressProgramWithoutSku()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                addressProgramResponse -> {
                                    dataManager.setSyncTime(calendar);
                                    dataManager.setHotLine(addressProgramResponse.getHotLine());
                                    dataManager.setTradePoints(addressProgramResponse.getTradePoints());
                                    final int[] doneSkuLoads = {0};
                                    disposables.add(
                                            Observable.fromIterable(addressProgramResponse.getTradePoints())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribeOn(Schedulers.io())
                                                    .subscribe(tradePoint -> disposables.add(
                                                            dataManager.skuByTradePointId(tradePoint.getId())
                                                                    .observeOn(AndroidSchedulers.mainThread())
                                                                    .subscribeOn(Schedulers.io())
                                                                    .subscribe(
                                                                            skuRealmList -> {
                                                                                doneSkuLoads[0]++;
                                                                                tradePoint.setSkus(skuRealmList);
                                                                                dataManager.setTradePoint(tradePoint);
                                                                                if (doneSkuLoads[0] >= addressProgramResponse.getTradePoints().size()) {
                                                                                    isLoading = false;
                                                                                    getMvpView().openMainActivity();
                                                                                    getMvpView().finishActivity();
                                                                                }
                                                                            },
                                                                            this::parseError
                                                                    )
                                                    ))
                                    );
                                },
                                this::parseError
                        )
                );
            } else {
                getMvpView().showNoInternetDialog();
            }
        } else {
            timer = new CountDownTimer(LAUNCH_TIME, TIMER_STEP) {
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    openNextActivityAfterTimer();
                }
            }.start();
        }
    }

    void onResume() {
        if (timer == null && !isLoading) {
            openNextActivityAfterTimer();
        }
    }

    void onStop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void openNextActivityAfterTimer() {
        if (dataManager.isAuth()) {
            getMvpView().openMainActivity();
            getMvpView().finishActivity();
        } else {
            getMvpView().openAuthActivity();
            getMvpView().finishActivity();
        }
    }

}