package ru.panmin.gtspro.ui.tredpoint.merchandise_trade_point;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.panmin.gtspro.R;

class MeAdapter extends RecyclerView.Adapter<MeAdapter.MeViewHolder> {

    @Inject
    MeAdapter() {
    }

    @NonNull
    @Override
    public MeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.me_item_trade_point, parent, false);
        return new MeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MeViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MeViewHolder extends RecyclerView.ViewHolder {
        public MeViewHolder(View itemView) {
            super(itemView);
        }

        public void bind() {
        }
    }
}
