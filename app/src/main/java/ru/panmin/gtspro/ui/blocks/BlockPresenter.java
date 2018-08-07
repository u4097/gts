package ru.panmin.gtspro.ui.blocks;

import java.util.function.ToDoubleBiFunction;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;
import timber.log.Timber;

public class BlockPresenter extends ToolbarPresenter<BlockMvpView>  {

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

    public void logout() {
        dataManager.clear();
        getMvpView().openLoginActivity();
        getMvpView().finishActivity();
    }

    public void getTradePoint(String tradePointId) {
        getMvpView().setTradePoint(dataManager.getTradePointById(tradePointId));
    }


    public void initViews() {
        getMvpView().initViews(dataManager.getFullName(), dataManager.getRole());
    }

    public void selectNewSortType(String sortType) {
        // TODO: 07/08/2018 Not implemented.
//            getMvpView().selectNewSortType(sortType);
    }

}