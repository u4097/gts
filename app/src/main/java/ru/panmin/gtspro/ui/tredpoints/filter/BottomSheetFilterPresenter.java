package ru.panmin.gtspro.ui.tredpoints.filter;

import android.text.TextUtils;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.base.BasePresenter;

class BottomSheetFilterPresenter extends BasePresenter<BottomSheetFilterMvpView> {

    private final DataManager dataManager;

    @Inject
    BottomSheetFilterPresenter(DataManager dataManager) {
        this.dataManager = dataManager;

    }

    @Override
    protected void dispose() {
    }

    public void initViews() {
        getMvpView().afterViews(dataManager.getSortType());
    }

    public void detachView(String sortType) {
        if (!TextUtils.equals(dataManager.getSortType(), sortType)) {
            dataManager.setSortType(sortType);
            getMvpView().selectNewSortType(sortType);
        }
        detachView();
    }

}