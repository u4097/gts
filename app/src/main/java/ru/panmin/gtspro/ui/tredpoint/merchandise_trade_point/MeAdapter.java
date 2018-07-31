package ru.panmin.gtspro.ui.tredpoint.merchandise_trade_point;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
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

class MeAdapter extends RecyclerView.Adapter<MeAdapter.MeViewHolder> {

    @Inject
    MeAdapter() {
    }

    private List<TradePoint> tradePoints = new ArrayList<>();
    private InfoClickListener infoClickListener;

    public void setData(List<TradePoint> tradePoints) {
        this.tradePoints = tradePoints;
        notifyDataSetChanged();
    }

    public void ClickListener(InfoClickListener infoClickListener) {
        this.infoClickListener = infoClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull MeViewHolder holder, int position) {
        holder.bind(tradePoints.get(position));
    }

    @NonNull
    @Override
    public MeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.me_item_trade_point, parent, false);
        return new MeViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return (tradePoints == null || tradePoints.isEmpty()) ? 0 : tradePoints.size();
    }

    interface InfoClickListener {
        void showInfo(TradePoint tradePoint);
    }

    class MeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_card)
        TextView name;
        @BindView(R.id.adres_card_me)
        TextView address;
        @BindView(R.id.btn_info)
        AppCompatImageView info;

        public MeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(TradePoint tradePoint) {
            name.setText(tradePoint.getSignboard().toString(itemView.getContext()));
            address.setText(tradePoint.getAddress().toString(itemView.getContext()));
            info.setOnClickListener(view -> infoClickListener.showInfo(tradePoint));
        }
    }
}
