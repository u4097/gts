package ru.panmin.gtspro.ui.blocks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Claim;
import ru.panmin.gtspro.data.models.Client;
import timber.log.Timber;

public class ClaimSvAdapter extends RecyclerView.Adapter<ClaimSvAdapter.ClaimVH> {

    private List<Claim> claimList = new ArrayList<>();
    private ClaimClickListener infoClickListener;
    private Client client;
    private Map<String, Client> clients;

    @Inject
    public ClaimSvAdapter() {
    }

    public void setData(List<Claim> claimList, Map<String,Client> clientMap) {
        this.claimList.clear();
        this.clients = clientMap;
        this.claimList.addAll(claimList);
        notifyDataSetChanged();
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_claim_sv, parent, false);
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

        @BindView(R.id.claimRoot)
        ViewGroup claimRoot;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.tvDateStart)
        TextView tvDateStart;
        @BindView(R.id.tvDateEnd)
        TextView tvDateEnd;

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
                tvDescription.setText(claim.getText());
            }
            if (claim.getCreationDate() != null) {
                tvDateStart.setText(getDateFormated(claim.getCreationDate()));
            }
            if (claim.getAppointDate() != null) {
                tvDateEnd.setText(getDateFormated(claim.getAppointDate()));
            }

            claimRoot.setOnClickListener(view -> infoClickListener.showClaim(claim));


            claimRoot.setOnClickListener(view -> infoClickListener.showClaim(claim));
        }
    }

    private String getDateFormated(String date) {
        date = date.substring(0, 10);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateObj = null;
        try {
            dateObj = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dateFormated = new SimpleDateFormat("MM/dd/yyyy").format(dateObj);
        return dateFormated;
    }

}
