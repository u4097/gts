package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_grop_sku;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablecheckrecyclerview.CheckableChildRecyclerViewAdapter;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablecheckrecyclerview.models.MultiCheckExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import javax.inject.Inject;

import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.SkuForAdapter;

public class GroupAdapter extends CheckableChildRecyclerViewAdapter<SkuNameGroupViewHolder, MultiCheckSkuViewHolder> {


    public GroupAdapter(List<? extends MultiCheckExpandableGroup> groups) {
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

            holder.setSkuName((SkuForAdapter) group.getItems().get(childIndex));
        }

        @Override
        public SkuNameGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_group_sku_recycler, parent, false);
            return new SkuNameGroupViewHolder(v);
        }

        @Override
        public void onBindGroupViewHolder(SkuNameGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.bind(group);
        }

        public static class GroupForAdapter extends MultiCheckExpandableGroup {

            public GroupForAdapter(String title, List items) {
                super(title, items);
            }

            @Override
            public void onChildClicked(int childIndex, boolean checked) {
                // TODO: 13.08.2018 присвоить интерфейс адаптеру, навесить на клик 
            }

        }


    }