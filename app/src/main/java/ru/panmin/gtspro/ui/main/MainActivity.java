package ru.panmin.gtspro.ui.main;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class MainActivity extends ToolbarActivity implements MainMvpView {

    @Inject MainPresenter mainPresenter;

    public MainActivity() {
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        mainPresenter.attachView(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
    }

    @Override
    protected void initViews() {
        setStateData();
    }

    @Override
    protected void detachView() {
        mainPresenter.detachView();
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