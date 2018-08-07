package ru.panmin.gtspro.ui.promoinfo.me;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import timber.log.Timber;

public class PromoInfoMeActivity extends ToolbarActivity implements PromoInfoMeMvpView {

    private static final String INTENT_KEY_PROMO_ID = "promo.id";

    @Inject
    PromoInfoMePresenter promoInfoPresenter;

    @BindView(R.id.tvClients) TextView tvClients;
    @BindView(R.id.tvAuthor) TextView tvAuthor;
    @BindView(R.id.tvPeriod) TextView tvPeriod;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvSku) TextView tvSku;

    private Promo promo = null;

    public PromoInfoMeActivity() {
    }

    public static Intent getStartIntent(Context context, String promoId) {
        Intent intent = new Intent(context, PromoInfoMeActivity.class);
        intent.putExtra(INTENT_KEY_PROMO_ID, promoId);
        return intent;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_promo_info_me;
    }

    @Override
    protected void attachView() {
        promoInfoPresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        promoInfoPresenter.getPromo(getIntent().getStringExtra(INTENT_KEY_PROMO_ID));
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void detachView() {
        promoInfoPresenter.detachView();
    }

    @Override
    protected EmptyBundle getEmptyBundle() {
        return null;
    }

    @Override
    protected void emptyButtonClick() {
    }

    @Override
    protected void errorButtonClick() {
    }

    @Override
    public void setPromo(Promo promo) {
        if (promo != null) {
            this.promo = promo;
            if (promo.getName() != null) {
                setTitle(promo.getName().toString(this));
            }
            if (promo.getClient() != null) {
                setValue(tvClients, promo.getClient().toString(this), R.string.label_clients);
            }
            if (promo.getAuthor() != null) {
                setValue(tvAuthor, promo.getAuthor().toString(this), R.string.label_author);
            }
            if (promo.getBeginDate() != null && promo.getFinishDate() != null) {
                setValue(tvPeriod, promo.getBeginDate() + " - " + promo.getFinishDate(), R.string.label_period);
            }
            if (promo.getDescription() != null) {
                setValue(tvDescription, promo.getDescription().toString(this), R.string.label_promo_description);
            }
            if (promo.getSkuIds() != null) {
                setValue(tvSku, promo.getSkuIds().toString(), R.string.label_promo_sku);
            }
        }

        setStateData();
    }

    @Override
    public void setValue(TextView tv, String text, Integer labelRes) {
        if (TextUtils.isEmpty(text)) {
            tv.setVisibility(View.GONE);
        } else {
            String label = getString(labelRes) + " ";
            Spannable spannable = new SpannableString(label + text);
            spannable.setSpan(new StyleSpan(Typeface.BOLD), 0, label.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(spannable);
            tv.setVisibility(View.VISIBLE);
        }
    }

}