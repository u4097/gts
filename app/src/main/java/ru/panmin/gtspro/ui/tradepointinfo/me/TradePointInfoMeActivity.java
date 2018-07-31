package ru.panmin.gtspro.ui.tradepointinfo.me;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class TradePointInfoMeActivity extends ToolbarActivity implements TradePointInfoMeMvpView {

    private static final String INTENT_KEY_TRADE_POINT = "trade.point";

    @Inject TradePointInfoMePresenter tradePointInfoMePresenter;

    private TradePoint tradePoint = null;

    public TradePointInfoMeActivity() {
    }

    public static Intent getStartIntent(Context context, TradePoint tradePoint) {
        Intent intent = new Intent(context, TradePointInfoMeActivity.class);
        intent.putExtra(INTENT_KEY_TRADE_POINT, tradePoint);
        return intent;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_trade_point_info_sv;
    }

    @Override
    protected void attachView() {
        tradePointInfoMePresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        tradePoint = getIntent().getParcelableExtra(INTENT_KEY_TRADE_POINT);
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
        setTitle(tradePoint.getSignboard().toString(this));
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void detachView() {
        tradePointInfoMePresenter.detachView();
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