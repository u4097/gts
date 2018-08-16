package ru.panmin.gtspro.utils;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MultiChoiceAdapter<I> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<I> items = new ArrayList<>();
    private SparseBooleanArray choiceItemPositions = new SparseBooleanArray();

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

    public List<I> getChoiceItems() {
        List<I> choiceItems = new ArrayList<>(choiceItemPositions.size());
        for (int i = 0; i < choiceItemPositions.size(); i++) {
            choiceItems.add(items.get(choiceItemPositions.keyAt(i)));
        }
        return choiceItems;
    }

    public List<Integer> getChoicePositions() {
        List<Integer> choicePositions = new ArrayList<>(choiceItemPositions.size());
        for (int i = 0; i < choiceItemPositions.size(); i++) {
            choicePositions.add(choiceItemPositions.keyAt(i));
        }
        return choicePositions;
    }

    protected void setChoiceClickListener(View view, MultiChoiceItemInterface multiChoiceItemInterface, int position) {
        view.setOnClickListener(v -> {
            choiceItem(position);
            if (multiChoiceItemInterface != null) {
                multiChoiceItemInterface.choiceItem();
            }
        });
    }

    protected void setChoiceClickListener(View view, int position) {
        setChoiceClickListener(view, null, position);
    }

    public void choiceItem(int position) {
        if (choiceItemPositions.get(position, false)) {
            choiceItemPositions.delete(position);
        } else {
            choiceItemPositions.put(position, true);
        }
        notifyItemChanged(position);
    }

    public void clearChoice() {
        List<Integer> choicePositions = getChoicePositions();
        choiceItemPositions.clear();
        for (Integer position : choicePositions) {
            notifyItemChanged(position);
        }
    }

    public boolean isChoiceItem(int position) {
        return getChoicePositions().contains(position);
    }

    protected interface MultiChoiceItemInterface {

        void choiceItem();

    }

}