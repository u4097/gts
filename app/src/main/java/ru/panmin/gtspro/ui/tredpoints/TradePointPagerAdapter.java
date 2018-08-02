package ru.panmin.gtspro.ui.tredpoints;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.panmin.gtspro.ui.base.BaseFragment;
import ru.panmin.gtspro.ui.tredpoints.map.MapFragment;
import ru.panmin.gtspro.ui.tredpoints.me.MeTradePointFragment;
import ru.panmin.gtspro.ui.tredpoints.me.MeTradePointMvpView;
import ru.panmin.gtspro.ui.tredpoints.sv.SwTradePointFragment;
import ru.panmin.gtspro.ui.tredpoints.sv.SwTradePointMvpView;

class TradePointPagerAdapter extends FragmentPagerAdapter {

    private static final int COUNT = 2;

    private final boolean isSupervisor;

    private MeTradePointMvpView meTradePointMvpView = null;
    private SwTradePointMvpView swTradePointMvpView = null;

    TradePointPagerAdapter(FragmentManager supportFragmentManager, boolean isSupervisor) {
        super(supportFragmentManager);
        this.isSupervisor = isSupervisor;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tiele;
        tiele = position == 0 ? "Торговые точки" : "Карта";
        return tiele;
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment = null;
        switch (position) {
            case 0:
                if (isSupervisor) {
                    fragment = SwTradePointFragment.createInstance();
                    swTradePointMvpView = (SwTradePointMvpView) fragment;
                } else {
                    fragment = MeTradePointFragment.createInstance();
                    meTradePointMvpView = (MeTradePointMvpView) fragment;
                }
                break;
            case 1:
                fragment = MapFragment.createInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    public void selectNewSortType(String sortType) {
        if (meTradePointMvpView != null) {
            meTradePointMvpView.selectNewSortType(sortType);
        } else if (swTradePointMvpView != null) {
            swTradePointMvpView.selectNewSortType(sortType);
        }
    }

}