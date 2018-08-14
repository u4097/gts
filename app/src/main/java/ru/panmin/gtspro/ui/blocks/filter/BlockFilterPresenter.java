package ru.panmin.gtspro.ui.blocks.filter;

import android.text.TextUtils;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.base.BasePresenter;

class BlockFilterPresenter extends BasePresenter<BlockFilterMvpView> {

    private final DataManager dataManager;

    @Inject
    BlockFilterPresenter(DataManager dataManager) {
        this.dataManager = dataManager;

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