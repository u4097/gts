package ru.panmin.gtspro.ui.tredpoints.filter;

import ru.panmin.gtspro.ui.base.MvpView;

interface BottomSheetFilterMvpView extends MvpView {

    void afterViews(String sortType);

    void selectNewSortType(String sortType);

}