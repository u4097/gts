package ru.panmin.gtspro.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import ru.panmin.gtspro.R;

public abstract class BaseSpinnerAdapter <T> extends ArrayAdapter<T> {

    @LayoutRes
    private static final int ITEM_RESOURCE_LAYOUT = R.layout.item_spinner;
    private static final int ITEM_SET_RESOURCE_LAYOUT = R.layout.item_set_spinner;
    private static final int ITEM_RESOURCE_LAYOUT_DROP_DOWN = R.layout.item_spinner_dropdown;

    private Context context;

    public BaseSpinnerAdapter(Context context, List<T> objects) {
        super(context, ITEM_RESOURCE_LAYOUT, objects);
        this.context = context;
    }

    public BaseSpinnerAdapter(Context context, T[] objects) {
        super(context, ITEM_RESOURCE_LAYOUT, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(context).inflate(ITEM_RESOURCE_LAYOUT, parent, false);
        AppCompatTextView textView = ((AppCompatTextView) view);
        textView.setText(getItemName(position));
        textView.post(() -> textView.setSingleLine(false));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(ITEM_RESOURCE_LAYOUT_DROP_DOWN, parent, false);
        AppCompatTextView textView = ((AppCompatTextView) view);
        textView.setText(getItemName(position));
        textView.post(() -> textView.setSingleLine(false));
        return view;
    }

    protected abstract String getItemName(int position);

}