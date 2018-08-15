package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_selected_sku;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedHashSet;

import javax.inject.Inject;

import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.SkuListElement;

class SelectedAdapter extends RecyclerView.Adapter<SelectedAdapter.SelectedViewHolder> {

    private LinkedHashSet<SkuListElement> selectedSku = new LinkedHashSet<>();

    @Inject
    SelectedAdapter(){}
    @NonNull
    @Override
    public SelectedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).
                inflate(R.layout.item_sku_recycler,
                        parent,
                        false);
        return new SelectedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedViewHolder holder, int position) {
      holder.bind();
    }


    @Override
    public int getItemCount() {
        return selectedSku.size();
    }

    public void selectSku(SkuListElement skuListElement) {
        selectedSku.add(skuListElement);
        notifyDataSetChanged();
    }

    public void deselectSku(SkuListElement skuListElement) {
        selectedSku.remove(skuListElement);
        notifyDataSetChanged();
    }


    public class SelectedViewHolder extends RecyclerView.ViewHolder {
        public SelectedViewHolder(View itemView) {
            super(itemView);
        }

        public void bind() {
        }
    }
}