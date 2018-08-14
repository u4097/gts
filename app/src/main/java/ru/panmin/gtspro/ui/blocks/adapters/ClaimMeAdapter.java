package ru.panmin.gtspro.ui.blocks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.models.Claim;
import ru.panmin.gtspro.data.models.Client;
import timber.log.Timber;

public class ClaimMeAdapter extends RecyclerView.Adapter<ClaimMeAdapter.ClaimVH> {

    private List<Claim> claimList = new ArrayList<>();
    private ClaimClickListener infoClickListener;
    private Client client;
    private Map<String, Client> clients;


    @Inject
    public ClaimMeAdapter() {
    }

    public void setData(List<Claim> claimList, Map<String,Client> clientMap) {
        this.clients = clientMap;
        this.claimList.clear();
        this.claimList.addAll(claimList);
        notifyDataSetChanged();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setInfoClickListener(ClaimClickListener infoClickListener) {
        this.infoClickListener = infoClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ClaimVH holder, int position) {
        holder.bind(claimList.get(position));
    }

    @NonNull
    @Override
    public ClaimVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_claim_me, parent, false);
        return new ClaimVH(v);
    }

    @Override
    public int getItemCount() {
        return (claimList == null || claimList.isEmpty()) ? 0 : claimList.size();
    }

    public interface ClaimClickListener {
        void showClaim(Claim claim);
    }

    class ClaimVH extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvSubtitle)
        TextView tvSubtitle;
        @BindView(R.id.ivStatus)
        AppCompatImageView ivStatus;
        @BindView(R.id.promoRoot)
        ViewGroup promoRoot;

        public ClaimVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Claim claim) {

            if (claim.getClientId() != null) {
                Timber.d("client id: %s",claim.getClientId());
                client = clients.get(claim.getClientId());

            }
            if (client != null) {
                tvTitle.setText(client.getName().toString());
            }
            if (claim.getText() != null) {
                tvSubtitle.setText(claim.getText());
            }
            promoRoot.setOnClickListener(view -> infoClickListener.showClaim(claim));
        }
    }
}
