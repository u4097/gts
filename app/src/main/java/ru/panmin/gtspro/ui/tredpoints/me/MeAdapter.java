package ru.panmin.gtspro.ui.tredpoints.me;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
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
import timber.log.Timber;

class MeAdapter extends RecyclerView.Adapter<MeAdapter.MeViewHolder> {

    private List<TradePoint> tradePoints = new ArrayList<>();
    private InfoClickListener infoClickListener;
    private Location location = null;
    private boolean isDistanceSort = false;

    @Inject
    MeAdapter() {
    }

    public void setData(Context context, List<TradePoint> tradePoints, String sortType) {
        this.tradePoints.clear();
        this.tradePoints.addAll(tradePoints);
        if (location != null) {
            for (TradePoint tradePoint : this.tradePoints) {
                tradePoint.setDistance(OtherUtils.distance(location, tradePoint.getCoordinates()));
            }
        }
        selectNewSortType(context, sortType);
    }

    public void setInfoClickListener(InfoClickListener infoClickListener) {
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

    public void selectNewSortType(Context context, String sortType) {
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
                        (tradePoint1, tradePoint2) -> tradePoint1.getSignboard().toString(context)
                                .compareTo(tradePoint2.getSignboard().toString(context))
                );
                break;
        }
        notifyDataSetChanged();
    }

    public void onLocationUpdated(Location location) {
        this.location = location;
        try {
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
        } catch (Exception e) {
            Timber.d(e);
        }

    }

    interface InfoClickListener {
        void showInfo(TradePoint tradePoint);
    }

    class MeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_card) TextView name;
        @BindView(R.id.adres_card_me) TextView address;
        @BindView(R.id.btn_info) AppCompatImageView info;

        MeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(TradePoint tradePoint) {
            name.setText(tradePoint.getSignboard().toString(itemView.getContext()));
            address.setText(tradePoint.getAddress().toString(itemView.getContext()));
            info.setOnClickListener(view -> infoClickListener.showInfo(tradePoint));
        }

    }

}