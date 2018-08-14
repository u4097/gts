package ru.panmin.gtspro.ui.blocks;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

public class BlockPresenter extends ToolbarPresenter<BlockMvpView> {

    private final DataManager dataManager;

    @Inject
    BlockPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void logout() {
        dataManager.clear();
        getMvpView().openLoginActivity();
        getMvpView().finishActivity();
    }

    public void initViews(String tradePointId) {
        getMvpView().initViews(
                dataManager.getRole(),
                dataManager.getTradePointById(tradePointId)
        );
    }

    public void selectNewSortType(String sortType) {
        // TODO: 07/08/2018 Not implemented.
    }

}