package ru.panmin.gtspro.ui.tredpoints.sv;

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
import ru.panmin.gtspro.ui.base.BottomSheetFragment;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressFragment;
import ru.panmin.gtspro.ui.tradepointinfo.sv.TradePointInfoSvActivity;
import ru.panmin.gtspro.ui.tredpoints.filter.BottomSheetFilter;


public class SwTradePointFragment extends ProgressFragment implements SwTradePointMvpView, SvAdapter.InfoClickListener {

    @Inject SwTradePointPresenter presenter;
    @Inject SvAdapter adapter;

    @BindView(R.id.recycler_trade_point) RecyclerView recyclerView;
    @BindView(R.id.floating_filter) FloatingActionButton filter;

    public static SwTradePointFragment createInstance() {
        return new SwTradePointFragment();
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
        initGpsConnect(location -> adapter.onLocationUpdated(location));
        initFilter();
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

    private void initFilter() {
        filter.setOnClickListener(view -> showDialogFragment(new BottomSheetFilter()));
    }

    private <T extends BottomSheetFragment> void showDialogFragment(T bottomSheetFilter) {
        String tag = bottomSheetFilter.getClass().getSimpleName();
        if (getChildFragmentManager().findFragmentByTag(tag) == null) {
            bottomSheetFilter.show(getChildFragmentManager(), bottomSheetFilter.getClass().getSimpleName());
        }
    }

    @Override
    protected void detachView() {
        presenter.detachView();
    }

    @Override
    public void setTradePoint(List<TradePoint> tradePoints, String sortType) {
        adapter.setData(getActivity(), tradePoints, sortType);
    }

    @Override
    public void selectNewSortType(String sortType) {
        adapter.selectNewSortType(getActivity(), sortType);
    }

    @Override
    public void showInfo(TradePoint tradePoint) {
        startActivity(TradePointInfoSvActivity.getStartIntent(getActivity(), tradePoint.getId()));
    }

}