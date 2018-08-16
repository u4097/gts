package ru.panmin.gtspro.utils;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SingleChoiceAdapter<I> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<I> items = new ArrayList<>();
    private Pair<I, Integer> choiceItemAndPosition = null;

    @Override
    public int getItemCount() {
        return items.size();
    }

    public int indexOf(I item) {
        return items.indexOf(item);
    }

    public I getItem(int position) {
        return items.get(position);
    }

    public List<I> getItems() {
        return items;
    }

    public void setItems(List<I> items) {
        this.items.clear();
        addItems(items);
    }

    public void addItems(List<I> items) {
        this.items.addAll(items);
    }

    public void addItem(I item) {
        addItems(Collections.singletonList(item));
    }

    @Nullable
    public I getChoiceItem() {
        return choiceItemAndPosition == null ? null : choiceItemAndPosition.first;
    }

    @Nullable
    public Integer getChoicePosition() {
        return choiceItemAndPosition == null ? null : choiceItemAndPosition.second;
    }

    protected void setChoiceClickListener(View view, SingleChoiceItemInterface singleChoiceItemInterface, I item, int position) {
        view.setOnClickListener(v -> {
            if (!isChoiceItem(item, position)) {
                Integer lastChoicePosition = null;
                if (getChoiceItem() != null) {
                    lastChoicePosition = getChoicePosition();
                }
                choiceItem(item, position);
                if (lastChoicePosition != null) {
                    notifyItemChanged(lastChoicePosition);
                }
                if (singleChoiceItemInterface != null) {
                    singleChoiceItemInterface.choiceItem();
                }
                notifyItemChanged(position);
            }
        });
    }

    protected void setChoiceClickListener(View view, I item, int position) {
        setChoiceClickListener(view, null, item, position);
    }

    public void choiceItem(I item, int position) {
        choiceItemAndPosition = new Pair<>(item, position);
    }

    public void clearChoice() {
        Integer choicePosition = getChoicePosition();
        choiceItemAndPosition = null;
        notifyItemChanged(choicePosition);
    }

    public boolean isChoiceItem(I item, int position) {
        return choiceItemAndPosition != null
                && choiceItemAndPosition.first.equals(item)
                && choiceItemAndPosition.second == position;
    }

    protected interface SingleChoiceItemInterface {

        void choiceItem();

    }

}
