package ru.panmin.gtspro.ui.blocks;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.blocks.holders.BlocksVH;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;
import timber.log.Timber;

public class BlockPresenter extends ToolbarPresenter<BlockMvpView> implements BlocksVH.OnTradePointBlockClickListener {

    private final DataManager dataManager;
    private BlockType.Type currentBlock = BlockType.Type.PROMO;

    @Inject
    BlockPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

    @Override
    public void onTradePointBlockClick(BlockType.Type blockType) {
        if (blockType != BlockType.Type.PROMO) {
            Timber.d("Блок " + blockType + " в разработке");
        }

        currentBlock = blockType;
    }
}