package ru.panmin.gtspro.ui.blocks.holders;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ru.lliepmah.HolderBuilder;
import ru.lliepmah.lib.DefaultViewHolder;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.blocks.model.PromoModel;

@HolderBuilder(R.layout.li_promo)
public class BlockPromoVH extends DefaultViewHolder<PromoModel> {
    private ImageView ivStatus = itemView.findViewById(R.id.ivStatus);
    private TextView tvTitle = itemView.findViewById(R.id.tvTitle);
    private TextView  tvSubtitle  = itemView.findViewById(R.id.tvSubtitle);
    private TextView  tvDateFrom  = itemView.findViewById(R.id.tvDateFrom);
    private TextView tvDateTo   = itemView.findViewById(R.id.tvDateTo);
    private ViewGroup promoRoot = itemView.findViewById(R.id.promoRoot);


    private OnPromoClickListener listener = null;

    public BlockPromoVH(View itemView, OnPromoClickListener listener) {
        super(itemView);
        this.listener = listener;
    }

    @Override
    public void bind(PromoModel model) {

//      ivStatus.setImageTintList(itemView.getContext(),itemView.getResources().getColorStateList(itemView.getContext(), R.color.orange));
        tvTitle.setText(model.getName().text(itemView.getResources()));
        tvSubtitle.setText(model.getSku().text(itemView.getResources()));
        tvDateFrom.setText(model.getBegin_date().getDateFormatted());
        tvDateTo.setText(model.getFinish_date().getDateFormatted());

        promoRoot.setOnClickListener(v -> listener.onPromoClick(model));

    }

    public interface OnPromoClickListener {
        void onPromoClick(PromoModel promoModel);
    }
}
