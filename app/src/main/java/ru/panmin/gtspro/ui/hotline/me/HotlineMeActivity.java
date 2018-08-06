package ru.panmin.gtspro.ui.hotline.me;

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
        implements HotlineMeMvpView {

    @Inject
    HotlineMePresenter hotlineMePresenter;

    @Inject
    HotlineMeAdapter hotlineMeAdapter;

    private TradePoint tradePoint = null;

    @BindView(R.id.recycler_hot_line)
    RecyclerView recycler_hot_line;

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
        hotlineMePresenter.getClient();
    }

    @Override
    protected void initViews() {

    }

    private void innitRecycler() {
        recycler_hot_line.setLayoutManager(new LinearLayoutManager(this));
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
        hotlineMeAdapter.setClient(tradePoint);
        innitRecycler();
    }
}
