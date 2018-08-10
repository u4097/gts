package ru.panmin.gtspro.ui.promoinfo;

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

public class PromoInfoActivity extends ToolbarActivity implements PromoInfoMvpView {

    private static final String INTENT_KEY_PROMO_ID = "promo.id";

    @Inject
    PromoInfoPresenter promoInfoPresenter;

    @BindView(R.id.tvClients)
    TextView tvClients;
    @BindView(R.id.tvAuthor)
    TextView tvAuthor;
    @BindView(R.id.tvPeriod)
    TextView tvPeriod;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvSku)
    TextView tvSku;

    private Promo promo = null;

    public PromoInfoActivity() {
    }

    public static Intent getStartIntent(Context context, String promoId) {
        Intent intent = new Intent(context, PromoInfoActivity.class);
        intent.putExtra(INTENT_KEY_PROMO_ID, promoId);
        return intent;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_promo_info;
    }

    @Override
    protected void attachView() {
        promoInfoPresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        promoInfoPresenter.getPromo(getIntent().getStringExtra(INTENT_KEY_PROMO_ID));
        setNavigationIcon(R.drawable.ic_back_arrow);
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
//        this.promo = promo;
//        setTitle(promo.getName().toString(this));
        setTitle("Промо");

/*        setValue(tvClients, promo.getClients().toString(), R.string.label_clients);
        setValue(tvAuthor, promo.getAuthor(), R.string.label_author);
        setValue(tvDescription, promo.getDescription(), R.string.label_promo_description);
        setValue(tvPeriod, "16-19 сентября", R.string.label_author);
        setValue(tvSku, promo.getSku(), R.string.label_promo_sku);*/

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