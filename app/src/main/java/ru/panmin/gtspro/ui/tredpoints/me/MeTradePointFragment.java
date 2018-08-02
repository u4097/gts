package ru.panmin.gtspro.ui.tredpoints.me;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressFragment;
import ru.panmin.gtspro.ui.tradepointinfo.me.TradePointInfoMeActivity;

public class MeTradePointFragment
        extends ProgressFragment
        implements MeTradePointMvpView, MeAdapter.InfoClickListener {

    @Inject MeTradePointPresenter presenter;
    @Inject MeAdapter adapter;

    @BindView(R.id.recycler_trade_point) RecyclerView recyclerView;
    @BindView(R.id.floating_filter) FloatingActionButton filter;

    public static MeTradePointFragment createInstance() {
        return new MeTradePointFragment();
    }

    @Override
    protected int getDataView() {
        return R.layout.fragment_recycler_trade_point;
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
        ((BaseActivity) Objects.requireNonNull(getActivity())).activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        presenter.attachView(this);
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setInfoClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    filter.hide();
                } else {
                    filter.show();
                }
            }
        });
        presenter.afterInitViews();
    }

    @Override
    protected void detachView() {
        presenter.detachView();
    }

    @Override
    public void setTradePoint(List<TradePoint> tradePoints) {
        adapter.setData(tradePoints);
    }

    @Override
    public void showInfo(TradePoint tradePoint) {
        startActivity(TradePointInfoMeActivity.getStartIntent(getActivity(), tradePoint.getId()));
    }

}