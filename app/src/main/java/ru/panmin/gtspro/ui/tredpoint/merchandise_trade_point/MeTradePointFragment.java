package ru.panmin.gtspro.ui.tredpoint.merchandise_trade_point;

import java.util.Objects;

import javax.inject.Inject;

import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarFragment;

public class MeTradePointFragment
        extends ToolbarFragment
        implements MeTradePointMvpView {


    @Inject
    MeTradePointPresenter presenter;

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int getDataView() {
        return 0;
    }

    @Override
    protected int getLayout() {
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
    }

    @Override
    protected void detachView() {
        presenter.detachView();
    }
}
