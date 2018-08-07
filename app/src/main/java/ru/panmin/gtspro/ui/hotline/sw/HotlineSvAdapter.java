package ru.panmin.gtspro.ui.hotline.sw;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Client;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.utils.TextUtils;

class HotlineSvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int CANCEL_VIEW = 0;
    private final static int CONTENT_VIEW = 2;
    private List<Client> clients = new ArrayList<>();
    private int maxLenght;
    private ClientAdapterClickListener clientAdapterClickListener;

    @Inject
    HotlineSvAdapter() {
    }

    public void setData(TradePoint tradePoint, Context context) {
        clients.clear();
        for (Client client : tradePoint.getClients()) {
            if (client.getName() != null && !TextUtils.isEmpty(client.getName().toString(context))) {
                clients.add(client);
            }
        }
        maxLenght = clients.size();
        notifyDataSetChanged();
    }

    public void setClientAdapterClickListener(ClientAdapterClickListener clientAdapterClickListener) {
        this.clientAdapterClickListener = clientAdapterClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position != maxLenght) {
            return CONTENT_VIEW;
        } else {
            return CANCEL_VIEW;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case CONTENT_VIEW:
                View content = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.client_hot_line_item, parent, false);
                return new HotlineSvAdapter.ViewHolderContent(content);
            case CANCEL_VIEW:
                View cancel = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.client_hot_line_item_cancel, parent, false);
                return new HotlineSvAdapter.ViewHolderCancel(cancel);
            default:
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.client_hot_line_item, parent, false);
                return new HotlineSvAdapter.ViewHolderContent(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() != CANCEL_VIEW) {
            HotlineSvAdapter.ViewHolderContent viewHolderContent = (HotlineSvAdapter.ViewHolderContent) holder;
            viewHolderContent.bind(clients.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return maxLenght + 1;
    }

    interface ClientAdapterClickListener {

        void clieintClick(String s);

        void cancelClick();
    }

    public class ViewHolderContent extends RecyclerView.ViewHolder {
        @BindView(R.id.text_client_hot_line_item)
        AppCompatTextView text_client_hot_line_item;

        ViewHolderContent(View content) {
            super(content);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Client client) {
            text_client_hot_line_item.setText(client.getName().toString(itemView.getContext()));
            text_client_hot_line_item.setOnClickListener(view -> clientAdapterClickListener.clieintClick(client.getName().toString(itemView.getContext())));
        }
    }

    public class ViewHolderCancel extends RecyclerView.ViewHolder {
        ViewHolderCancel(View cancel) {
            super(cancel);
            itemView.setOnClickListener(view -> clientAdapterClickListener.cancelClick());
        }
    }
}
