package ru.panmin.gtspro.ui.claiminfo.sv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pixplicity.multiviewpager.MultiViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Claim;
import ru.panmin.gtspro.data.models.Client;
import ru.panmin.gtspro.data.models.Photo;
import ru.panmin.gtspro.ui.blocks.adapters.ClaimPhotoViewPagerAdapter;
import ru.panmin.gtspro.ui.blocks.adapters.PhotoSliderHelper;
import ru.panmin.gtspro.ui.claiminfo.me.ChatActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import timber.log.Timber;

public class ClaimInfoSvActivity extends ToolbarActivity implements ClaimInfoSvMvpView {

    private static final String INTENT_KEY_PROMO_ID = "promo.id";

    @Inject
    ClaimInfoSvPresenter claimInfoMePresenter;

    @BindView(R.id.tvAuthor) TextView tvAuthor;

    @BindView(R.id.vpPhoto) MultiViewPager vpPhoto;

    @BindView(R.id.tvNumber) TextView tvNumber;

    @BindView(R.id.tvClaimMessage) TextView tvText;

    @BindView(R.id.tvDateStart) TextView tvDateStart;

    @BindView(R.id.tvDateFinish) TextView tvDateFinish;

    @BindView(R.id.tvClaimType) TextView tvType;

    @BindView(R.id.btnChat) Button btnChat;


    private Claim claim = null;
    private Client client = null;

    public ClaimInfoSvActivity() {
    }

    public static Intent getStartIntent(Context context, String promoId) {
        Intent intent = new Intent(context, ClaimInfoSvActivity.class);
        intent.putExtra(INTENT_KEY_PROMO_ID, promoId);
        return intent;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_claim_info_sv;
    }

    @Override
    protected void attachView() {
        claimInfoMePresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        claimInfoMePresenter.getClaim(getIntent().getStringExtra(INTENT_KEY_PROMO_ID));
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
    }

    @Override
    protected void initViews() {
        vpPhoto.setAdapter(new ClaimPhotoViewPagerAdapter(this, getPhotoList()));
        btnChat.setOnClickListener(v -> startActivity(ChatActivity.getStartIntent(this, "")));
    }

    private List<PhotoSliderHelper> getPhotoList() {
        List<PhotoSliderHelper> photoList = new ArrayList<>();

        if (this.claim.getPhotos() != null) {
            for (Photo photo : claim.getPhotos()) {
                Timber.d("Photos url: %s", photo.getUrl());
                Timber.d("Photos comment: %s", photo.getComment());
                photoList.add(new PhotoSliderHelper("Photo 1", R.drawable.photo1));
                photoList.add(new PhotoSliderHelper("Photo 2", R.drawable.photo2));
                photoList.add(new PhotoSliderHelper("Photo 3", R.drawable.photo1));
            }
        }

        return photoList;
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

    @Override
    public Claim getClaim() {
        return this.claim;
    }

    @Override
    public void setClaim(Claim claim) {
        this.claim = claim;
        if (claim != null) {
            if (claim.getNumber() != null) {
                setValue(tvNumber, claim.getNumber(), R.string.label_nuber);
            }

            if (claim.getText() != null) {
                setValue(tvText, claim.getText(), R.string.label_message_claim);
            }

            if (claim.getCreationDateWithFormat() != null) {
                setValue(tvDateStart, claim.getCreationDateWithFormat(), R.string.label_date_start);
            }
            if (claim.getAppointDateWithFormat() != null) {
                setValue(tvDateFinish, claim.getAppointDate(), R.string.label_date_end);
            } else {
                setValue(tvDateFinish, "-", R.string.label_date_end);
            }


            if (claim.getType().getName() != null) {
                setValue(tvType, claim.getType().getName().toString(), R.string.label_claim_type);
            } else {
                setValue(tvType, "-", R.string.label_claim_type);
            }

            setValue(tvAuthor, "-", R.string.label_author);

        }

        setStateData();
    }

    @Override
    public void setClient(Client client) {
        if (client != null) {
            this.client = client;
            setTitle(client.getName().toString());
        }
    }

}