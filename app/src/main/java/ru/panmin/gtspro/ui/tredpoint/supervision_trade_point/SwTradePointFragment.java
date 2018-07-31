package ru.panmin.gtspro.ui.tredpoint.supervision_trade_point;

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


public class SwTradePointFragment extends ProgressFragment implements SwTradePointMvpView {

    @Inject
    SwTradePointPresenter presenter;
    @Inject
    SvAdapter adapter;
    @BindView(R.id.recycler_trade_point)
    RecyclerView recyclerView;
    @BindView(R.id.floating_filter)
    FloatingActionButton filter;

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
