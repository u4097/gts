package ru.panmin.gtspro.ui.login;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.base.BasePresenter;

class LoginPresenter extends BasePresenter<LoginMvpView> {

    private final DataManager dataManager;

    @Inject
    LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void enter(String userName, String password) {
        getMvpView().hideKeyboard();
        if (!isLoginValid(userName)) {
            getMvpView().showLoginValidError();
        } else if (!isPasswordValid(password)) {
            getMvpView().showPasswordValidError();
        } else {
            getMvpView().showProgressDialog();
            disposables.add(dataManager.auth(userName, password)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            authResponse -> {
                                getMvpView().hideProgressDialog();
                                getMvpView().openSplashActivity();
                                getMvpView().finishActivity();
                            },
                            throwable -> {
                                parseError(throwable);
                                getMvpView().hideProgressDialog();
                            }
                    )
            );
        }
    }

}