package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_all_sku;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.SkuForAdapter;
import ru.panmin.gtspro.data.models.SkuListElement;

class AllAdapter extends RecyclerView.Adapter<AllAdapter.AllSkuViewHolder> {

    private List<SkuForAdapter> skuForAdapters = new ArrayList<>();

    @Inject
    AllAdapter() {
    }

    @NonNull
    @Override
    public AllAdapter.AllSkuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).
                inflate(R.layout.item_sku_recycler,
                        parent,
                        false);
        return new AllSkuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllAdapter.AllSkuViewHolder holder, int position) {
        holder.bind(skuForAdapters.toArray()[position]);
    }

    @Override
    public int getItemCount() {
        return skuForAdapters.size();
    }

    public void setData(ArraySet<SkuListElement> skuListElements) {
        //skuForAdapters.addAll(skuListElements);
    }

    public class AllSkuViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_multicheck_sku_name)
        CheckedTextView list_item_multicheck_sku_name;

        AllSkuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Object o) {
            list_item_multicheck_sku_name.setText(o.toString());
        }
    }

}