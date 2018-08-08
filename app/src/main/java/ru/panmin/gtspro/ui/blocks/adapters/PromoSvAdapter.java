package ru.panmin.gtspro.ui.blocks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Promo;

public class PromoSvAdapter extends RecyclerView.Adapter<PromoSvAdapter.PromoVH> {

    private List<Promo> promoList = new ArrayList<>();
    private PromoClickListener infoClickListener;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

    @Inject
    public PromoSvAdapter() {
    }

    public void setData(List<Promo> promoList) {
        this.promoList.clear();
        this.promoList.addAll(promoList);
        notifyDataSetChanged();
    }

    public void setInfoClickListener(PromoClickListener infoClickListener) {
        this.infoClickListener = infoClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull PromoVH holder, int position) {
        holder.bind(promoList.get(position));
    }

    @NonNull
    @Override
    public PromoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_promo_sv, parent, false);
        return new PromoVH(v);
    }

    @Override
    public int getItemCount() {
        return (promoList == null || promoList.isEmpty()) ? 0 : promoList.size();
    }

    public interface PromoClickListener {
        void showPromo(Promo promo);
    }

    class PromoVH extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.tvDateStart)
        TextView tvDateStart;
        @BindView(R.id.tvDateEnd)
        TextView tvDateEnd;
        @BindView(R.id.tvAuthor)
        TextView tvAuthor;
        @BindView(R.id.tvAuthor_label)
        TextView tvAuthor_label;
        @BindView(R.id.promoRoot)
        ViewGroup promoRoot;

        public PromoVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Promo promo) {
            if (promo.getAuthor() != null) {
                tvAuthor.setText(promo.getAuthor().toString(itemView.getContext()));
            }
            if (promo.getFinishDate() != null) {
                tvDateEnd.setText(promo.getFinishDate());
            }
            if (promo.getBeginDate() != null) {
                tvDateStart.setText(promo.getBeginDate());
            }
            if (promo.getDescription() != null) {
                tvDescription.setText(promo.getDescription().toString(itemView.getContext()));
            }

            tvTitle.setText(promo.getName().toString(itemView.getContext()));
            promoRoot.setOnClickListener(view -> infoClickListener.showPromo(promo));
        }
    }


}
