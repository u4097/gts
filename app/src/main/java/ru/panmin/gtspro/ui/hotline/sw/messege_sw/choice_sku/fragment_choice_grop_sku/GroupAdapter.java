package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_grop_sku;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablecheckrecyclerview.CheckableChildRecyclerViewAdapter;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import javax.inject.Inject;

import ru.panmin.gtspro.R;


public class GroupAdapter extends CheckableChildRecyclerViewAdapter<SkuGroupViewHolder, MultiCheckSkuViewHolder> {


    public GroupAdapter(List<? extends CheckedExpandableGroup> groups) {
        super(groups);
    }


    @Override
    public MultiCheckSkuViewHolder onCreateCheckChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sku_recycler, parent, false);
        return new MultiCheckSkuViewHolder(v);
    }

    @Override
    public void onBindCheckChildViewHolder(MultiCheckSkuViewHolder holder, int flatPosition, CheckedExpandableGroup group, int childIndex) {
        holder.bind();
    }

    @Override
    public SkuGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group_sku_recycler, parent, false);
        return new SkuGroupViewHolder(v);
    }

    @Override
    public void onBindGroupViewHolder(SkuGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
    }

    public static class GroupForAdapter extends CheckedExpandableGroup {

        public GroupForAdapter(String title, List items) {
            super(title, items);
        }

        @Override
        public void onChildClicked(int childIndex, boolean checked) {
        }

    }


}