package ru.panmin.gtspro.ui.base;


import io.reactivex.disposables.Disposable;
import ru.panmin.gtspro.utils.RxUtils;

public abstract class BasePresenter<T extends MvpView> implements Presenter<T> {

    protected Disposable disposable;
    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        RxUtils.dispose(disposable);
        dispose();
        mMvpView = null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    protected abstract void dispose();

}