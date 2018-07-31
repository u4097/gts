package ru.panmin.gtspro.ui.tredpoint.supervision_trade_point;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.panmin.gtspro.R;


public class SvAdapter extends RecyclerView.Adapter<SvAdapter.SvViewHolder> {

    @Inject
    SvAdapter() {
    }

    @NonNull
    @Override
    public SvAdapter.SvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.me_item_trade_point, parent, false);
        return new SvAdapter.SvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SvAdapter.SvViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class SvViewHolder extends RecyclerView.ViewHolder {
        public SvViewHolder(View itemView) {
            super(itemView);
        }

        public void bind() {
        }
    }
}
