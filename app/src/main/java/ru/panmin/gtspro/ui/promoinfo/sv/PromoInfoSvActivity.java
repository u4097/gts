package ru.panmin.gtspro.ui.promoinfo.sv;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.ui.forms.FormsActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import timber.log.Timber;

public class PromoInfoSvActivity extends ToolbarActivity implements PromoInfoSvMvpView {

    private static final String INTENT_KEY_PROMO_ID = "promo.id";

    @Inject PromoInfoSvPresenter promoInfoSvPresenter;

    @BindView(R.id.tvClients) TextView tvClients;
    @BindView(R.id.tvAuthor) TextView tvAuthor;
    @BindView(R.id.tvPeriod) TextView tvPeriod;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvSku) TextView tvSku;
    @BindView(R.id.tvAuthorBottom) TextView tvAuthorBottom;
    @BindView(R.id.btnResume) AppCompatButton btnResume;

    private Promo promo = null;

    public PromoInfoSvActivity() {
    }

    public static Intent getStartIntent(Context context, String promoId) {
        Intent intent = new Intent(context, PromoInfoSvActivity.class);
        intent.putExtra(INTENT_KEY_PROMO_ID, promoId);
        return intent;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_promo_info_sv;
    }

    @Override
    protected void attachView() {
        promoInfoSvPresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        promoInfoSvPresenter.getPromo(getIntent().getStringExtra(INTENT_KEY_PROMO_ID));
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void detachView() {
        promoInfoSvPresenter.detachView();
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
            setTitle(promo.getName().toString());
            Timber.d(promo.getClient().toString());

            if (promo.getClient() != null) {
                setValue(tvClients, promo.getClient().toString(), R.string.label_clients);
            }
            if (promo.getAuthor() != null) {
                setValue(tvAuthor, promo.getAuthor().toString(), R.string.label_author);
                tvAuthorBottom.setText(promo.getAuthor().toString());
            }
            if (promo.getBeginDate() != null && promo.getFinishDate() != null) {
                setValue(tvPeriod, promo.getBeginDateWithFormat() + " - " + promo.getFinishDateWithFormat(), R.string.label_period);
            }
            if (promo.getDescription() != null) {
                setValue(tvDescription, promo.getDescription().toString(), R.string.label_promo_description);
            }
            if (promo.getSkuIds() != null) {
                setValue(tvSku, promo.getSkuIds().toString(), R.string.label_promo_sku);
            }

            btnResume.setOnClickListener(view -> startActivity(FormsActivity.getStartIntent(this, promo.getId())));
        }
        setStateData();
    }

}