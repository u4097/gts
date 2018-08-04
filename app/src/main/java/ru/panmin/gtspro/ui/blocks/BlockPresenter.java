package ru.panmin.gtspro.ui.blocks;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;
import timber.log.Timber;

public class BlockPresenter extends ToolbarPresenter<BlockMvpView> {

    private final DataManager dataManager;
    private BlockType.Type currentBlock = BlockType.Type.PROMO;

    @Inject
    BlockPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

    public void onTradePointBlockClick(BlockType.Type blockType) {
        if (blockType != BlockType.Type.PROMO) {
            Timber.d("Блок " + blockType + " в разработке");
        }

        currentBlock = blockType;
    }

    public void getTradePoint(String tradePointId) {
        getMvpView().setTradePoint(dataManager.getTradePointById(tradePointId));
    }

    public void logout() {
        dataManager.clear();
        getMvpView().openLoginActivity();
        getMvpView().finishActivity();
    }

}