package ru.panmin.gtspro.ui.hotline.me;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.TradePoint;

public class HotlineMeAdapter extends RecyclerView.Adapter<HotlineMeAdapter.HotlineMeViewHolder> {


    @Inject
    HotlineMeAdapter() {
    }

    private final static int CANCEL_VIEW = 0;

    private final static int CONTENT_VIEW = 2;

    int maxLenght;

    public void setClient(TradePoint tradePointById) {

    }

    @Override
    public int getItemViewType(int position) {
        return position != maxLenght ? getItemViewType(CONTENT_VIEW) : getItemViewType(CANCEL_VIEW);
    }

    @NonNull
    @Override
    public HotlineMeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case CONTENT_VIEW:
                View content = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.client_hot_line_item, parent, false);
                return new ViewHolderContent(content);
            case CANCEL_VIEW:
                View cancel = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.client_hot_line_item_cancel, parent, false);
                return new ViewHolderCancel(cancel);
            default:
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.client_hot_line_item, parent, false);
                return new ViewHolderContent(v);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull HotlineMeViewHolder holder, int position) {
        if (holder.getItemViewType() != CANCEL_VIEW) {

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class HotlineMeViewHolder extends RecyclerView.ViewHolder {
        public HotlineMeViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class ViewHolderContent extends HotlineMeViewHolder {
        public ViewHolderContent(View content) {
            super(content);
        }
    }

    private class ViewHolderCancel extends HotlineMeViewHolder {
        public ViewHolderCancel(View cancel) {
            super(cancel);
        }
    }
}
