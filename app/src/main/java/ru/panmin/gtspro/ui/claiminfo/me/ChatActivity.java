package ru.panmin.gtspro.ui.claiminfo.me;

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

import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Claim;
import ru.panmin.gtspro.data.models.Client;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class ChatActivity extends ToolbarActivity implements ClaimInfoMeMvpView {

    private static final String INTENT_KEY_CLAIM_ID = "promo.id";

    @Inject
    ClaimInfoMePresenter claimInfoMePresenter;

    private Claim claim = null;

    public ChatActivity() {
    }

    public static Intent getStartIntent(Context context, String promoId) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(INTENT_KEY_CLAIM_ID, promoId);
        return intent;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_chat;
    }

    @Override
    protected void attachView() {
        claimInfoMePresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
//      claimInfoMePresenter.getClaim(getIntent().getStringExtra(INTENT_KEY_CLAIM_ID));
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
        setTitle("Чат");
    }


    @Override
    protected void initViews() {
//      vpPhoto.setAdapter(new ClaimPhotoViewPagerAdapter(this, getPhotoList()));
        setStateData();
    }


    @Override
    protected void detachView() {
        claimInfoMePresenter.detachView();
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
    public Claim getClaim() {
        return this.claim;
    }

    @Override
    public void setClient(Client client) {
        if (client != null) {
//            this.client = client;
        }
    }

    @Override
    public void setClaim(Claim claim) {
        if (claim != null) {
            this.claim = claim;
        }

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