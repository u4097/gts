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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Claim;
import timber.log.Timber;

public class ClaimSvAdapter extends RecyclerView.Adapter<ClaimSvAdapter.ClaimVH> {

    private List<Claim> claimList = new ArrayList<>();
    private ClaimClickListener infoClickListener;

    @Inject
    public ClaimSvAdapter() {
    }

    public void setData(List<Claim> promoList) {
        this.claimList.clear();
        this.claimList.addAll(promoList);
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

/*        @BindView(R.id.ivStatus)
        AppCompatImageView ivStatus;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvSubtitle)
        TextView tvSubtitle;
        @BindView(R.id.tvDateStart)
        TextView tvDateStart;
        @BindView(R.id.tvDateEnd)
        TextView tvDateEnd;*/

        public ClaimVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Claim claim) {
/*            if (claim.getName() != null) {
                tvTitle.setText(claim.getName().toString(itemView.getContext()));
                Timber.d("promo title: %s", claim.getName().toString(itemView.getContext()));
            }
            if (claim.getClaimText() != null) {
                tvSubtitle.setText(claim.getClaimText().toString(itemView.getContext()));
                Timber.d("promo description: %s",claim.getClaimText().toString(itemView.getContext()));
            }

            if (claim.getBeginDate() != null) {
                tvDateStart.setText(claim.getBeginDate());
            }
            if (claim.getFinishDate() != null) {
                tvDateEnd.setText(claim.getFinishDate());
            }*/

            claimRoot.setOnClickListener(view -> infoClickListener.showClaim(claim));
        }
    }
}
