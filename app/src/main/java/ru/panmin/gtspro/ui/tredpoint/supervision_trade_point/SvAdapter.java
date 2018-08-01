package ru.panmin.gtspro.ui.tredpoint.supervision_trade_point;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.TradePoint;


public class SvAdapter extends RecyclerView.Adapter<SvAdapter.SvViewHolder> {

    private List<TradePoint> tradePoints = new ArrayList<>();
    private SvAdapter.InfoClickListener infoClickListener;

    @Inject
    SvAdapter() {
    }

    public void setData(List<TradePoint> tradePoints) {
        this.tradePoints.clear();
        this.tradePoints.addAll(tradePoints);
        notifyDataSetChanged();
    }

    public void setInfoClickListener(InfoClickListener infoClickListener) {
        this.infoClickListener = infoClickListener;
    }

    @NonNull
    @Override
    public SvAdapter.SvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sw_item_trade_point, parent, false);
        return new SvAdapter.SvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SvAdapter.SvViewHolder holder, int position) {
        holder.bind(tradePoints.get(position));
    }

    @Override
    public int getItemCount() {
        return (tradePoints == null || tradePoints.isEmpty()) ? 0 : tradePoints.size();
    }

    interface InfoClickListener {
        void showInfo(TradePoint tradePoint);
    }

    class SvViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_card)
        TextView name;
        @BindView(R.id.adres_card_sw)
        TextView address;
        @BindView(R.id.tv_claims_quantity)
        AppCompatTextView claimsQuantity;
        @BindView(R.id.btn_info)
        AppCompatImageView info;

        public SvViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("DefaultLocale")
        public void bind(TradePoint tradePoint) {
            name.setText(tradePoint.getSignboard().toString(itemView.getContext()));
            address.setText(tradePoint.getAddress().toString(itemView.getContext()));
            claimsQuantity.setText(String.format("%d", tradePoint.getClaims().size()));
            info.setOnClickListener(view -> infoClickListener.showInfo(tradePoint));
        }

    }
}