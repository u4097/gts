package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_grop_sku;

import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;

import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.SkuForAdapter;

class MultiCheckSkuViewHolder extends CheckableChildViewHolder {

    @BindView(R.id.list_item_multicheck_sku_name)
    CheckedTextView list_item_multicheck_sku_name;

    public MultiCheckSkuViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public Checkable getCheckable() {
        return list_item_multicheck_sku_name;
    }

    public void setSkuName(SkuForAdapter skuName) {
        list_item_multicheck_sku_name.setText(skuName.getName().toString());
    }
}
