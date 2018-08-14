package ru.panmin.gtspro.ui.blocks.viewmodel;

import java.util.ArrayList;
import java.util.List;

import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.blocks.model.Block;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.blocks.model.BlocksModel;

public class BlockViewModel {
    BlocksModel blocksModel;

    public void loadData(TradePoint tradePoint) {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(BlockType.Type.CLAIMS, tradePoint.getClaims().size(), false));
        blocks.add(new Block(BlockType.Type.PROMO, tradePoint.getPromos().size(), false));
        blocks.add(new Block(BlockType.Type.PHOTO_REPORT, tradePoint.getPhotoreports().size(), false));
        blocks.add(new Block(BlockType.Type.REPORT, tradePoint.getReports().size(), false));
        blocks.add(new Block(BlockType.Type.PLANOGRAM, 0, false));
        blocks.add(new Block(BlockType.Type.HOT_LINE, 0, false));
        blocks.add(new Block(BlockType.Type.SKU, 0, false));
        blocks.add(new Block(BlockType.Type.STATISTICS, 0, false));
        this.blocksModel = new BlocksModel();
        this.blocksModel.setBlocks(blocks);

    }

    public BlocksModel getBlocks() {
        return blocksModel;
    }

}
