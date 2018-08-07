package ru.panmin.gtspro.ui.blocks.filter;

import ru.panmin.gtspro.ui.base.MvpView;

interface BlockFilterMvpView extends MvpView {

    void afterViews(String sortType);

    void selectNewSortType(String sortType);

}