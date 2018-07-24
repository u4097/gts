package ru.panmin.gtspro.ui.progress;

import android.text.TextUtils;

import ru.panmin.gtspro.ui.base.BasePresenter;
import ru.panmin.gtspro.utils.OtherUtils;

public abstract class ProgressPresenter<T extends ProgressMvpView> extends BasePresenter<T> {

    protected void requestError(Throwable e) {
        if (isViewAttached()) {
            if (OtherUtils.isInternetError(e)) {
                getMvpView().setStateInternetError();
            } else {
                if (TextUtils.isEmpty(e.getMessage())) {
                    getMvpView().setStateError(e.getMessage());
                } else {
                    getMvpView().setStateUnknownServerError();
                }
            }
        }
    }

}