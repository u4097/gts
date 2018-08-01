package ru.panmin.gtspro.ui.promoinfo;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class PromoInfoActivity extends ToolbarActivity implements PromoInfoMvpView {

    private static final String INTENT_KEY_TRADE_POINT = "trade.point";

    @Inject
    PromoInfoPresenter promoInfoPresenter;

    private TradePoint tradePoint = null;

    public PromoInfoActivity() {
    }

    public static Intent getStartIntent(Context context, TradePoint tradePoint) {
        Intent intent = new Intent(context, PromoInfoActivity.class);
        intent.putExtra(INTENT_KEY_TRADE_POINT, tradePoint);
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
//        tradePoint = getIntent().getParcelableExtra(INTENT_KEY_TRADE_POINT);
        setNavigationIcon(R.drawable.ic_back_arrow);
        setNavigationOnClickListener(view -> finishActivity());
//        setTitle(tradePoint.getSignboard().toString(this));
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

}