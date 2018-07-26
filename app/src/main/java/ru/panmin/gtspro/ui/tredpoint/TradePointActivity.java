package ru.panmin.gtspro.ui.tredpoint;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.widget.Adapter;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class TradePointActivity
        extends ToolbarActivity
        implements TradePointMvpView {

    public TradePointActivity() {
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, TradePointActivity.class);
    }

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Inject
    TradePointPresenter tradePointPresenter;

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getDataView() {
        return 0;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_trade_point;
    }


    @Override
    protected void attachView() {
        tradePointPresenter.attachView(this);
    }

    @Override
    protected void detachView() {
        tradePointPresenter.detachView();
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initViews() {
        setStateData();
        initNavigationDrawer();
        initViewPager();
    }

    private void initViewPager() {
        PagerAdapter adapter = new TradePoinPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
       /* viewPager.getOffscreenPageLimit()=viewPager.setAdapter(adapter);*/
    }

    private void initNavigationDrawer() {
        inflateMenu(R.menu.trade_point);
        setOnMenuItemClickListener(item -> {
            if (drawer.isDrawerOpen(Gravity.END)) {
                drawer.closeDrawer(Gravity.END);
            } else {
                drawer.openDrawer(Gravity.END);
            }
            return false;
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
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
