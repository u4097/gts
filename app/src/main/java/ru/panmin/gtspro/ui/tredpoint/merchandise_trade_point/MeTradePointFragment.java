package ru.panmin.gtspro.ui.tredpoint.merchandise_trade_point;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressFragment;

public class MeTradePointFragment
        extends ProgressFragment
        implements MeTradePointMvpView {

    @Inject
    MeTradePointPresenter presenter;
    @Inject
    MeAdapter adapter;
    @BindView(R.id.recycler_trade_point)
    RecyclerView recyclerView;
    @BindView(R.id.floating_filter)
    FloatingActionButton filter;

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
        setStateData();
        initRecycler();
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

    }

    @Override
    protected void detachView() {
        presenter.detachView();
    }
}
