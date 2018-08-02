package ru.panmin.gtspro.ui.splash;

import android.os.CountDownTimer;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.base.BasePresenter;
import ru.panmin.gtspro.utils.RxUtils;

class SplashPresenter extends BasePresenter<SplashMvpView> {

    private static final int LAUNCH_TIME = 1500;
    private static final int TIMER_STEP = LAUNCH_TIME;

    private final DataManager dataManager;

    private CountDownTimer timer;

    @Inject
    SplashPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

    void init(boolean isOnline) {
        if (dataManager.isAuth() && dataManager.isNeedUpdateDB()) {
            dataManager.clearDataBase();
            if (isOnline) {
                Calendar calendar = Calendar.getInstance();
                RxUtils.dispose(disposable);
                disposable = dataManager.addressProgram()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                addressProgramResponse -> {
                                    dataManager.setSyncTime(calendar);
                                    dataManager.setAutoCheckoutTime(addressProgramResponse.getAutoCheckoutTime());
                                    dataManager.setTradePointRadius(addressProgramResponse.getTradePointRadius());
                                    dataManager.setHotLine(addressProgramResponse.getHotLine());
                                    dataManager.setTradePoints(addressProgramResponse.getTradePoints());
                                    getMvpView().openMainActivity();
                                    getMvpView().finishActivity();
                                },
                                throwable -> {
                                    parseError(throwable);
                                }
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
        if (timer == null && disposable == null) {
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