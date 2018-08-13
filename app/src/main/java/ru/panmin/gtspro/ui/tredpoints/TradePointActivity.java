package ru.panmin.gtspro.ui.tredpoints;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.customviews.VectorsSupportTextView;
import ru.panmin.gtspro.ui.login.LoginActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressActivity;
import ru.panmin.gtspro.utils.Constants;
import ru.panmin.gtspro.utils.LocaleManager;

public class TradePointActivity
        extends ProgressActivity
        implements TradePointMvpView {

    private static final String INTENT_KEY_WITH_OPEN_DRAWER = "with.open.drawer";

    @Inject
    TradePointPresenter tradePointPresenter;

    @BindView(R.id.drawer_layout) DrawerLayout drawer;

    @BindView(R.id.view_pager) ViewPager viewPager;

    @BindView(R.id.tab_layout) TabLayout tabLayout;

    @BindView(R.id.menu_hamburger) AppCompatImageView hamburger;

    @BindView(R.id.avatarImageNavigationView) AppCompatTextView avatarImageNavigationView;

    @BindView(R.id.nameTextNavigationView) AppCompatTextView nameTextNavigationView;

    @BindView(R.id.statusTextNavigationView) AppCompatTextView statusTextNavigationView;

    @BindView(R.id.buttonLanguageRu) AppCompatButton buttonLanguageRu;

    @BindView(R.id.buttonLanguageEn) AppCompatButton buttonLanguageEn;

    @BindView(R.id.exitTextBottom) VectorsSupportTextView exitTextBottom;

    private TradePointPagerAdapter tradePointPagerAdapter = null;

    public TradePointActivity() {
    }

    public static Intent getStartIntent(Context context) {
        return getStartIntent(context, false);
    }

    public static Intent getStartIntent(Context context, boolean withOpenDrawer) {
        Intent intent = new Intent(context, TradePointActivity.class);
        intent.putExtra(INTENT_KEY_WITH_OPEN_DRAWER, withOpenDrawer);
        return intent;
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
        tradePointPresenter.initViews();
    }

    void showHamburger() {
        hamburger.setVisibility(View.VISIBLE);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    void hideHamburger() {
        hamburger.setVisibility(View.GONE);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void setNewLanguage(String newLanguage) {
        if (!TextUtils.equals(newLanguage, LocaleManager.getLanguage())) {
            LocaleManager.setNewLocale(this, newLanguage);
            startActivity(TradePointActivity.getStartIntent(this, true));
            finishActivity();
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

    @Override
    public void initViews(String fullName, String role) {
        nameTextNavigationView.setText(fullName);

        switch (role) {
            case Constants.ROLE_SUPERVISOR:
                tradePointPagerAdapter = new TradePointPagerAdapter(getSupportFragmentManager(), true);
                avatarImageNavigationView.setText(R.string.role_supervisor_short);
                statusTextNavigationView.setText(R.string.role_supervisor);
                statusTextNavigationView.setVisibility(View.VISIBLE);
                break;
            case Constants.ROLE_MERCHANDISER:
                tradePointPagerAdapter = new TradePointPagerAdapter(getSupportFragmentManager(), false);
                avatarImageNavigationView.setText(R.string.role_merchandiser_short);
                statusTextNavigationView.setText(R.string.role_merchandiser);
                statusTextNavigationView.setVisibility(View.VISIBLE);
                break;
            default:
                tradePointPagerAdapter = new TradePointPagerAdapter(getSupportFragmentManager(), false);
                avatarImageNavigationView.setText(R.string.role_unknown);
                statusTextNavigationView.setVisibility(View.GONE);
                break;
        }

        viewPager.setAdapter(tradePointPagerAdapter);
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

        if (getIntent().getBooleanExtra(INTENT_KEY_WITH_OPEN_DRAWER, false)) {
            drawer.openDrawer(Gravity.END, false);
        }

        if (TextUtils.equals(Constants.LANGUAGE_RUSSIAN, LocaleManager.getLanguage())) {
            buttonLanguageRu.setBackgroundResource(R.drawable.azure_button_background);
            buttonLanguageRu.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            buttonLanguageRu.setBackgroundResource(R.drawable.white_button_background);
            buttonLanguageRu.setTextColor(ContextCompat.getColor(this, R.color.azure));
        }

        if (TextUtils.equals(Constants.LANGUAGE_ENGLISH, LocaleManager.getLanguage())) {
            buttonLanguageEn.setBackgroundResource(R.drawable.azure_button_background);
            buttonLanguageEn.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            buttonLanguageEn.setBackgroundResource(R.drawable.white_button_background);
            buttonLanguageEn.setTextColor(ContextCompat.getColor(this, R.color.azure));
        }

        buttonLanguageRu.setOnClickListener(view -> setNewLanguage(Constants.LANGUAGE_RUSSIAN));
        buttonLanguageEn.setOnClickListener(view -> setNewLanguage(Constants.LANGUAGE_ENGLISH));

        hamburger.setOnClickListener(view -> {
            if (drawer.isDrawerOpen(Gravity.END)) {
                drawer.closeDrawer(Gravity.END);
            } else {
                drawer.openDrawer(Gravity.END);
            }
        });

        exitTextBottom.setOnClickListener(view -> tradePointPresenter.exit());
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void selectNewSortType(String sortType) {
        tradePointPagerAdapter.selectNewSortType(sortType);
    }

}