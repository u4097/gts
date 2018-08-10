package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_selected_sku;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.LinkedHashSet;

import ru.panmin.gtspro.data.models.SkuListElement;

class SelectedAdapter extends RecyclerView.Adapter {

    private LinkedHashSet<SkuListElement> selectedSku = new LinkedHashSet<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

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

}