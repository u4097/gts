package ru.panmin.gtspro.ui.tredpoints.sv;

import android.annotation.SuppressLint;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.utils.Constants;
import ru.panmin.gtspro.utils.OtherUtils;


public class SvAdapter extends RecyclerView.Adapter<SvAdapter.SvViewHolder> {

    private List<TradePoint> tradePoints = new ArrayList<>();
    private SvAdapter.InfoClickListener infoClickListener;
    private Location location = null;
    private boolean isDistanceSort = false;

    @Inject
    SvAdapter() {
    }

    public void setData(List<TradePoint> tradePoints, String sortType) {
        this.tradePoints.clear();
        this.tradePoints.addAll(tradePoints);
        if (location != null) {
            for (TradePoint tradePoint : this.tradePoints) {
                tradePoint.setDistance(OtherUtils.distance(location, tradePoint.getCoordinates()));
            }
        }
        selectNewSortType(sortType);
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

    public void selectNewSortType(String sortType) {
        switch (sortType) {
            case Constants.SORT_TYPE_TIME:
                isDistanceSort = false;
                break;
            case Constants.SORT_TYPE_DISTANCE:
                isDistanceSort = true;
                Collections.sort(
                        tradePoints,
                        (tradePoint1, tradePoint2) -> Double.compare(tradePoint1.getDistance(), tradePoint2.getDistance())
                );
                break;
            case Constants.SORT_TYPE_ALPHABET:
                isDistanceSort = false;
                Collections.sort(
                        tradePoints,
                        (tradePoint1, tradePoint2) -> tradePoint1.getSignboard().toString()
                                .compareTo(tradePoint2.getSignboard().toString())
                );
                break;
        }
        notifyDataSetChanged();
    }

    public void onLocationUpdated(Location location) {
        this.location = location;
        if (location != null) {
            for (TradePoint tradePoint : this.tradePoints) {
                tradePoint.setDistance(OtherUtils.distance(location, tradePoint.getCoordinates()));
            }
        }
        if (isDistanceSort) {
            Collections.sort(
                    tradePoints,
                    (tradePoint1, tradePoint2) -> Double.compare(tradePoint1.getDistance(), tradePoint2.getDistance())
            );
        }
        notifyDataSetChanged();
    }

    interface InfoClickListener {
        void showInfo(TradePoint tradePoint);
    }

    class SvViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_card) TextView name;
        @BindView(R.id.adres_card_sw) TextView address;
        @BindView(R.id.tv_claims_quantity) AppCompatTextView claimsQuantity;
        @BindView(R.id.btn_info) AppCompatImageView info;

        SvViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("DefaultLocale")
        void bind(TradePoint tradePoint) {
            name.setText(tradePoint.getSignboard().toString());
            address.setText(tradePoint.getAddress().toString());
            claimsQuantity.setText(String.format("%d", tradePoint.getClaims().size()));
            info.setOnClickListener(view -> infoClickListener.showInfo(tradePoint));
        }

    }

}