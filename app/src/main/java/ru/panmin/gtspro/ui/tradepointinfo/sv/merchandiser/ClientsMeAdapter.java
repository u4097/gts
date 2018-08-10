package ru.panmin.gtspro.ui.tradepointinfo.sv.merchandiser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import ru.panmin.gtspro.data.models.Merchandiser;

public class ClientsMeAdapter extends RecyclerView.Adapter<ClientsMeAdapter.ClientsViewHolder> {

    private List<Client> clients = new ArrayList<>();

    @Inject
    ClientsMeAdapter() {
    }

    public void setData(Merchandiser merchandiser, Context context) {
        clients.clear();
        for (Client client : merchandiser.getClients()) {
            if (client.getName() != null && !TextUtils.isEmpty(client.getName().toString(context))) {
                clients.add(client);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.me_item_client, parent, false);
        return new ClientsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsViewHolder holder, int position) {
        holder.bind(clients.get(position), position);
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class ClientsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.client_number_text)
        AppCompatTextView client_number_text;

        @BindView(R.id.client_name_text)
        AppCompatTextView client_name_text;

        public ClientsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Client client, int position) {
            client_number_text.setText(String.valueOf(position + 1));
            client_name_text.setText(client.getName().toString(itemView.getContext()));


        }
    }
}
