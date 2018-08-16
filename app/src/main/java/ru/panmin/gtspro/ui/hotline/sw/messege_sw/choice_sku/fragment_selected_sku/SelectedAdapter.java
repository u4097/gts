package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_selected_sku;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import java.util.LinkedHashSet;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.SkuListElement;

class SelectedAdapter extends RecyclerView.Adapter<SelectedAdapter.SelectedViewHolder> {

    private LinkedHashSet<SkuListElement> selectedSku = new LinkedHashSet<>();

    private SelectedAdapter.ChekListener chekListener;

    @Inject
    SelectedAdapter(){
    }

    public void setChekListener(ChekListener chekListener){
        this.chekListener = chekListener;
    }


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
        holder.bind((SkuListElement) selectedSku.toArray()[position]);
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


    interface ChekListener{
        void chekSku();
    }
     class SelectedViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_multicheck_sku_name)
        CheckedTextView list_item_multicheck_sku_name;

        SelectedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(SkuListElement skuListElement) {
            list_item_multicheck_sku_name.setText(skuListElement.getName().toString());

        }
    }
}