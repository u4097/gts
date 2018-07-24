package ru.panmin.gtspro.utils;

import io.reactivex.disposables.Disposable;

public class RxUtils {

    private RxUtils() {
    }

    public static void dispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}