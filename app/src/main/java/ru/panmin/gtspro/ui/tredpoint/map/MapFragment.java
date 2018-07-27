package ru.panmin.gtspro.ui.tredpoint.map;

import java.util.Objects;

import javax.inject.Inject;

import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarFragment;


public class MapFragment
        extends ToolbarFragment
        implements MapMvpView {

    public static MapFragment createInstance() {
        return new MapFragment();
    }

    @Inject
    MapPresenter presenter;

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int getDataView() {
        return 0;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_map;
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
