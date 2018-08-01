package ru.panmin.gtspro.ui.blocks.holders;


import android.view.View;
import android.widget.TextView;

import ru.lliepmah.HolderBuilder;
import ru.lliepmah.lib.DefaultViewHolder;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.blocks.model.BlockType;

@HolderBuilder(R.layout.li_block_title)
public class BlockTitleVH extends DefaultViewHolder<BlockType> {

    TextView tvTitle;

    public BlockTitleVH(View itemView) {
        super(itemView);
        this.tvTitle = itemView.findViewById(R.id.tvTitle);
    }

    @Override
    public void bind(BlockType model) {
        tvTitle.setText(model.getTitleRes());
    }
}
