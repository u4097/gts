package ru.panmin.gtspro.ui.fullscreenimage;

import java.util.List;

import ru.panmin.gtspro.data.models.Photo;
import ru.panmin.gtspro.ui.base.MvpView;

interface FullscreenImageMvpView extends MvpView {

    void setData(List<Photo> photos);

}