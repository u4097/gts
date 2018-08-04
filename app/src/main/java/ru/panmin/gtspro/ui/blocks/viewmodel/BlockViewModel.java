package ru.panmin.gtspro.ui.blocks.viewmodel;

import java.util.ArrayList;
import java.util.List;

import ru.panmin.gtspro.ui.blocks.model.Block;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.blocks.model.BlocksModel;

public class BlockViewModel implements IBlocksViewModel {

    BlocksModel blocksModel;

    @Override
    public void onDataLoaded() {

    }

    @Override
    public void loadData(String tradePointId) {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(BlockType.Type.CLAIMS, 1, false));
        blocks.add(new Block(BlockType.Type.PROMO, 3, false));
        blocks.add(new Block(BlockType.Type.PHOTO_REPORT, 2, false));
        blocks.add(new Block(BlockType.Type.REPORT, 3, false));
        blocks.add(new Block(BlockType.Type.PLANOGRAM, 0, false));
        blocks.add(new Block(BlockType.Type.HOT_LINE, 1, false));
        blocks.add(new Block(BlockType.Type.SKU, 0, false));
        blocks.add(new Block(BlockType.Type.STATISTICS, 3, false));
        this.blocksModel = new BlocksModel();
        this.blocksModel.setBlocks(blocks);

    }

    public BlocksModel getBlocks() {
        return blocksModel;
    }

}