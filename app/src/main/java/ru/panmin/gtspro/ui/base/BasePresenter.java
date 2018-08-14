package ru.panmin.gtspro.ui.base;


import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import ru.panmin.gtspro.data.models.responses.BaseResponse;
import ru.panmin.gtspro.utils.GsonUtils;
import timber.log.Timber;

public abstract class BasePresenter<T extends MvpView> implements Presenter<T> {

    protected CompositeDisposable disposables = new CompositeDisposable();

    private T mMvpView = null;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        disposables.clear();
        mMvpView = null;
    }

    protected T getMvpView() {
        return mMvpView;
    }

    protected void parseError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            Response<?> response = httpException.response();
            ResponseBody errorBody = response.errorBody();
            BaseResponse baseResponse = null;
            if (errorBody != null) {
                Gson gson = GsonUtils.getGson();
                try {
                    baseResponse = gson.fromJson(errorBody.string(), BaseResponse.class);
                } catch (IOException e) {
                    Timber.d(e);
                }
            }
            if (baseResponse != null) {
                getMvpView().showError(baseResponse.getErrors().getDetail());
            } else {
                getMvpView().showUnknownServerError();
            }
        } else if (throwable instanceof IOException) {
            getMvpView().showUnknownServerError();
        } else {
            getMvpView().showUnknownError();
        }
        Timber.d(throwable);
    }

    protected boolean isLoginValid(String login) {
        return !TextUtils.isEmpty(login);
    }

    protected boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password);
    }

}