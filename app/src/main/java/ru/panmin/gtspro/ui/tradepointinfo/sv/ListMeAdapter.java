package ru.panmin.gtspro.ui.tradepointinfo.sv;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Merchandiser;


public class ListMeAdapter extends RecyclerView.Adapter<ListMeAdapter.ListMeHolder> {

    private List<Merchandiser> merchandisers = new ArrayList<>();
    private MeClickListener meClickListener;

    @Inject
    ListMeAdapter() {
    }

    public void setData(List<Merchandiser> merchandisers) {
        this.merchandisers.clear();
        this.merchandisers.addAll(merchandisers);
        notifyDataSetChanged();
    }

    public void setMeClickListener(MeClickListener meClickListener) {
        this.meClickListener = meClickListener;
    }

    interface MeClickListener {
        void showInfo(Merchandiser merchandisers);
    }

    @NonNull
    @Override
    public ListMeAdapter.ListMeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sw_item_info_trade_point, parent, false);
        return new ListMeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMeAdapter.ListMeHolder holder, int position) {
        holder.bind(merchandisers.get(position));
    }

    @Override
    public int getItemCount() {
        if (merchandisers != null) {
            return merchandisers.size();
        } else {
            return 0;
        }
    }


    class ListMeHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_me_recycler_item)
        AppCompatTextView name;

        @BindView(R.id.marker_image)
        AppCompatImageView marker;

        public ListMeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Merchandiser merchandiser) {
            itemView.setOnClickListener(view -> meClickListener.showInfo(merchandiser));
            name.setText(merchandiser.getName());
        }
    }
}
