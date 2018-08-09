package ru.panmin.gtspro.ui.claiminfo.sv;

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
import ru.panmin.gtspro.data.models.Claim;
import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import timber.log.Timber;

public class ClaimInfoSvActivity extends ToolbarActivity implements ClaimInfoSvMvpView {

    private static final String INTENT_KEY_CLAIM_ID = "claim.id";

    @Inject
    ClaimInfoSvPresenter claimInfoSvPresenter;

    @BindView(R.id.tvAuthor)
    TextView tvAuthor;
    @BindView(R.id.tvAuthorBottom)
    TextView tvAuthorBottom;


    private Claim claim = null;

    public ClaimInfoSvActivity() {
    }

    public static Intent getStartIntent(Context context, String claimId) {
        Intent intent = new Intent(context, ClaimInfoSvActivity.class);
        intent.putExtra(INTENT_KEY_CLAIM_ID, claimId);
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
        claimInfoSvPresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        claimInfoSvPresenter.getPromo(getIntent().getStringExtra(INTENT_KEY_CLAIM_ID));
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void detachView() {
        claimInfoSvPresenter.detachView();
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
    public void setClaim(Claim claim) {
        if (claim != null) {
            this.claim = claim;
            setTitle(claim.getName().toString(this));

            if (claim.getAuthor() != null) {
                setValue(tvAuthor, claim.getAuthor().toString(this), R.string.label_author);
                tvAuthorBottom.setText(claim.getAuthor().toString(this));
            }
            if (claim.getBeginDate() != null && claim.getFinishDate() != null) {
//                setValue(tvPeriod, claim.getBeginDate() + " - " + claim.getFinishDate(), R.string.label_period);
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