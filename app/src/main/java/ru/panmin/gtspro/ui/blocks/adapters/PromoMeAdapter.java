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
import ru.panmin.gtspro.data.models.Promo;

public class PromoMeAdapter extends RecyclerView.Adapter<PromoMeAdapter.PromoVH> {

    private List<Promo> promoList = new ArrayList<>();
    private InfoClickListener infoClickListener;

    @Inject
    public PromoMeAdapter() {
    }

    public void setData(List<Promo> promoList) {
        this.promoList.clear();
        this.promoList.addAll(promoList);
        notifyDataSetChanged();
    }

    public void setInfoClickListener(InfoClickListener infoClickListener) {
        this.infoClickListener = infoClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull PromoVH holder, int position) {
        holder.bind(promoList.get(position));
    }

    @NonNull
    @Override
    public PromoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_promo_me, parent, false);
        return new PromoVH(v);
    }

    @Override
    public int getItemCount() {
        return (promoList == null || promoList.isEmpty()) ? 0 : promoList.size();
    }

    public interface InfoClickListener {
        void showPromo(Promo promo);
    }

    class PromoVH extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvSubtitle)
        TextView tvSubtitle;
        @BindView(R.id.ivStatus)
        AppCompatImageView ivStatus;
        @BindView(R.id.promoRoot)
        ViewGroup promoRoot;
        @BindView(R.id.tvDateFrom)
        TextView tvDateFrom;
        @BindView(R.id.tvDateTo)
        TextView tvDateTo;

        PromoVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Promo promo) {
            if (promo.getName() != null) {
                tvTitle.setText(promo.getName().toString());
            }
            if (promo.getDescription() != null) {
                tvSubtitle.setText(promo.getDescription().toString());
            }
            if (promo.getBeginDate() != null) {
                tvDateFrom.setText(
                        String.format(
                                "%s %s\n%s %s",
                                tvDateFrom.getContext().getString(R.string.from),
                                promo.getBeginDateWithFormat(),
                                tvDateFrom.getContext().getString(R.string.to),
                                promo.getFinishDateWithFormat()
                        )
                );
            }

            promoRoot.setOnClickListener(view -> infoClickListener.showPromo(promo));
        }
    }

}