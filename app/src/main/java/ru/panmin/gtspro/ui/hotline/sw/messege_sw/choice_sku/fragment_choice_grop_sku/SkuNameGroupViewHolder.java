package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_grop_sku;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;

class SkuNameGroupViewHolder extends GroupViewHolder {

    @BindView(R.id.list_item_sku_group_name)
    AppCompatTextView list_item_sku_group_name;


    public SkuNameGroupViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ExpandableGroup group) {
        list_item_sku_group_name.setText(group.getTitle());
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        list_item_sku_group_name.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_up_arrow,
                0);
    }

    private void animateCollapse() {
        list_item_sku_group_name.setCompoundDrawablesWithIntrinsicBounds(0,
                0,
                R.drawable.ic_down_arrow,
                0);
    }

}
