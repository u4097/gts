package ru.panmin.gtspro.ui.hotline.me;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressActivity;

public class HotlineMeActivity
        extends ProgressActivity
        implements HotlineMeMvpView, HotlineMeAdapter.ClientAdapterClickListener {

    private static final String INTENT_KEY_TRADE_POINT_ID = "trade.point.id";
    @Inject
    HotlineMePresenter hotlineMePresenter;
    @Inject
    HotlineMeAdapter hotlineMeAdapter;
    @BindView(R.id.recycler_hot_line)
    RecyclerView recycler_hot_line;
    private TradePoint tradePoint = null;

    public HotlineMeActivity() {
    }

    public static Intent getStartIntent(Context context, String tradePointId) {
        Intent intent = new Intent(context, HotlineMeActivity.class);
        intent.putExtra(INTENT_KEY_TRADE_POINT_ID, tradePointId);
        return intent;
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_hot_line;
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
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        hotlineMePresenter.attachView(this);
    }

    @Override
    protected void initViews() {
        hotlineMePresenter.getClient(getIntent().getStringExtra(INTENT_KEY_TRADE_POINT_ID));
    }

    private void innitRecycler() {
        recycler_hot_line.setLayoutManager(new LinearLayoutManager(this));
        hotlineMeAdapter.setClientAdapterClickListener(this);
        recycler_hot_line.setAdapter(hotlineMeAdapter);
        setStateData();
    }

    @Override
    protected void detachView() {
        hotlineMePresenter.detachView();
    }

    @Override
    public void setClient(TradePoint tradePointById) {
        tradePoint = tradePointById;
        hotlineMeAdapter.setClient(tradePoint, HotlineMeActivity.this);
        innitRecycler();
    }

    @Override
    public void clieintClick(String clientName) {

    }

    @Override
    public void cancelClick() {
        finish();
    }
}
