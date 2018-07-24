package ru.panmin.gtspro.ui.login;

import android.text.TextUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.base.BasePresenter;
import ru.panmin.gtspro.utils.RxUtils;
import timber.log.Timber;

class LoginPresenter extends BasePresenter<LoginMvpView> {

    private final DataManager dataManager;

    @Inject
    LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

    public void enter(String userName, String password) {
        RxUtils.dispose(disposable);
        disposable = dataManager.auth(userName, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        authResponse -> {
                            if (!TextUtils.isEmpty(authResponse.getToken())){
                                dataManager.setToken(authResponse.getToken());
                            }
                            getMvpView().openMainActivity();
                            getMvpView().finishActivity();
                        },
                        throwable -> Timber.d(throwable)
                );
    }

}