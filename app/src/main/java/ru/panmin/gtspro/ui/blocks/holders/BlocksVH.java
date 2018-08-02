package ru.panmin.gtspro.ui.blocks.holders;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import ru.lliepmah.HolderBuilder;
import ru.lliepmah.HolderConstructor;
import ru.lliepmah.lib.DefaultViewHolder;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.blocks.model.Block;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.blocks.model.BlocksModel;

@HolderBuilder(R.layout.li_block)
public class BlocksVH extends DefaultViewHolder<BlocksModel> {

    private OnTradePointBlockClickListener listener = null;
    private Map<BlockType.Type, Holder> tradePointBlockViews;

    @HolderConstructor
    public BlocksVH(View itemView, OnTradePointBlockClickListener listener) {
        super(itemView);
        this.listener = listener;
        tradePointBlockViews = new HashMap<>();
        tradePointBlockViews.put(BlockType.Type.CLAIMS,
                new BlocksVH.Holder(itemView.findViewById(R.id.btnClaims), itemView.findViewById(R.id.tCounterClaims)));
        tradePointBlockViews.put(BlockType.Type.PROMO,
                new BlocksVH.Holder(itemView.findViewById(R.id.btnPromo), itemView.findViewById(R.id.tCounterPromo)));
        tradePointBlockViews.put(BlockType.Type.PHOTO_REPORT,
                new BlocksVH.Holder(itemView.findViewById(R.id.btnPhotoReport), itemView.findViewById(R.id.tCounterPhotoReport)));
        tradePointBlockViews.put(BlockType.Type.REPORT,
                new BlocksVH.Holder(itemView.findViewById(R.id.btnReport), itemView.findViewById(R.id.tCounterReport)));
        tradePointBlockViews.put(BlockType.Type.SKU,
                new BlocksVH.Holder(itemView.findViewById(R.id.btnSku), itemView.findViewById(R.id.tCounterSku)));
        tradePointBlockViews.put(BlockType.Type.PLANOGRAM,
                new BlocksVH.Holder(itemView.findViewById(R.id.btnPlanogram), itemView.findViewById(R.id.tCounterPlanogram)));
        tradePointBlockViews.put(BlockType.Type.HOT_LINE,
                new BlocksVH.Holder(itemView.findViewById(R.id.btnHotLine), itemView.findViewById(R.id.tCounterHotLine)));
        tradePointBlockViews.put(BlockType.Type.STATISTICS,
                new BlocksVH.Holder(itemView.findViewById(R.id.btnStatistics), itemView.findViewById(R.id.tCounterStatistics)));

        for (Map.Entry<BlockType.Type, Holder> entry :
                tradePointBlockViews.entrySet()) {

            entry.getValue().btn.setOnClickListener(view -> listener.onTradePointBlockClick(entry.getKey()));

        }
    }

    @Override
    public void bind(BlocksModel model) {
        for (Block block : model.getBlocks()
                ) {
            Holder holder = tradePointBlockViews.get(block.getType());
            if (holder != null) {
                TextView tvBadge = holder.getTvBadge();
                String count = block.getSize().toString();
                tvBadge.setText(count);
                if (block.getSize() == 0) {
                    tvBadge.setVisibility(View.INVISIBLE);
                } else {
                    tvBadge.setVisibility(View.VISIBLE);
                }


                FloatingActionButton btn = holder.getBtn();
                btn.setBackgroundTintList(itemView.getResources().getColorStateList(R.color.btn_selector));

                btn.setSelected(block.isSelected());
                tvBadge.setSelected(block.isSelected());

            }
        }
    }


/*    public BlocksVH(View itemView) {
        super(itemView);
//        this.listener = null;
    }*/


    public interface OnTradePointBlockClickListener {
        void onTradePointBlockClick(BlockType.Type blockType);
    }

    class Holder {
        FloatingActionButton btn;
        TextView tvBadge;

        public Holder(FloatingActionButton btn, TextView tvBadge) {
            this.btn = btn;
            this.tvBadge = tvBadge;
        }

        public FloatingActionButton getBtn() {
            return btn;
        }

        public TextView getTvBadge() {
            return tvBadge;
        }
    }

}
