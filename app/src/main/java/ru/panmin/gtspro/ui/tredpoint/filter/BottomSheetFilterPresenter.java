package ru.panmin.gtspro.ui.tredpoint.filter;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.base.BasePresenter;


public class BottomSheetFilterPresenter extends BasePresenter<BottomSheetFilterMvpView> {

    private final DataManager dataManager;

    @Inject
    BottomSheetFilterPresenter(DataManager dataManager) {
        this.dataManager = dataManager;

    }

    @Override
    protected void dispose() {
    }

}
