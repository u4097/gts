package ru.panmin.gtspro.ui.fullscreenimage;

import java.util.Objects;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.base.BasePresenter;

class FullscreenImagePresenter extends BasePresenter<FullscreenImageMvpView> {

    private final DataManager dataManager;

    @Inject
    FullscreenImagePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    void getAnswerById(String answerId) {
        getMvpView().setData(Objects.requireNonNull(dataManager.getAnswerById(answerId)).getPhotoList());
    }

}