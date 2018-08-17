package ru.panmin.gtspro.ui.blocks;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.models.Client;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

public class BlockPresenter extends ToolbarPresenter<BlockMvpView> {

    private final DataManager dataManager;
    private BlockType.Type currentBlock = BlockType.Type.CLAIMS;

    @Inject
    BlockPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    public BlockType.Type getCurrentBlock() {
        return currentBlock;
    }

    public void onTradePointBlockClick(BlockType.Type blockType) {
        if (blockType != BlockType.Type.PROMO) {
            getMvpView().setBlockTitle("Блок " + blockType + " в разработке");
        } else {
            getMvpView().setBlockTitle("Промо");
        }

        currentBlock = blockType;
        getMvpView().initBlockData(blockType);
    }

    public void logout() {
        dataManager.clear();
        getMvpView().openLoginActivity();
        getMvpView().finishActivity();
    }


    public Client getClientById(String clientId) {
        return dataManager.getClientById(clientId);
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