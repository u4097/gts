package ru.panmin.gtspro.ui.splash;

import android.os.CountDownTimer;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.base.BasePresenter;

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

    void init() {
        timer = new CountDownTimer(LAUNCH_TIME, TIMER_STEP) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                openNextActivity();
            }
        }.start();
    }

    void onResume() {
        if (timer == null) {
            openNextActivity();
        }
    }

    void onStop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void openNextActivity() {
        if (dataManager.isAuth()) {
            getMvpView().openMainActivity();
        } else {
            getMvpView().openAuthActivity();
        }
        getMvpView().finishActivity();
    }

}