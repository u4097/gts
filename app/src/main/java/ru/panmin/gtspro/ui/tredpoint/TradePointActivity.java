package ru.panmin.gtspro.ui.tredpoint;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
import android.view.View;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressActivity;

public class TradePointActivity
        extends ProgressActivity
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

    @BindView(R.id.menu_hamburger)
    AppCompatImageView hamburger;

    @BindView(R.id.ruBottomNavigationView)
    AppCompatButton ruBotton;

    @BindView(R.id.enBottomNavigationView)
    AppCompatButton enBotton;

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
    protected void initViews() {
        setStateData();
        initNavigationDrawer();
        initViewPager();
        languageButton();
    }

    private void languageButton() {
        ruBotton.setOnClickListener(view -> {
            ruBotton.setBackgroundDrawable(ContextCompat.getDrawable(TradePointActivity.this, R.drawable.blue_background));
            enBotton.setBackgroundDrawable(ContextCompat.getDrawable(TradePointActivity.this, R.drawable.white_background));
        });

        enBotton.setOnClickListener(view -> {
            enBotton.setBackgroundDrawable(ContextCompat.getDrawable(TradePointActivity.this, R.drawable.blue_background));
            ruBotton.setBackgroundDrawable(ContextCompat.getDrawable(TradePointActivity.this, R.drawable.white_background));
        });
    }

    private void initViewPager() {
        boolean b = true;
        PagerAdapter adapter = new TradePoinPagerAdapter(getSupportFragmentManager(), true);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(Objects.requireNonNull(viewPager.getAdapter()).getCount());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    showHamburger();
                } else {
                    hideHamburger();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initNavigationDrawer() {
        hamburger.setOnClickListener(view -> {
            if (drawer.isDrawerOpen(Gravity.END)) {
                drawer.closeDrawer(Gravity.END);
            } else {
                drawer.openDrawer(Gravity.END);
            }

        });

    }

    void showHamburger() {
        hamburger.setVisibility(View.VISIBLE);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    void hideHamburger() {
        hamburger.setVisibility(View.GONE);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
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
