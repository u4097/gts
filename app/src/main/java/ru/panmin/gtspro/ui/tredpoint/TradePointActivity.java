package ru.panmin.gtspro.ui.tredpoint;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class TradePointActivity extends ToolbarActivity implements TradePointMvpView {

    @Inject
    TradePointPresenter tradePointPresenter;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    public TradePointActivity() {
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, TradePointActivity.class);
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int getDataView() {
        return R.layout.activity_tredpoint;
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
        tradePointPresenter.attachView(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void detachView() {
        tradePointPresenter.detachView();
    }

    protected void openDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.openDrawer(GravityCompat.END);
        }
    }
}
