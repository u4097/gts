package ru.panmin.gtspro.ui.tredpoint;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.panmin.gtspro.ui.tredpoint.map.MapFragment;
import ru.panmin.gtspro.ui.tredpoint.merchandise_trade_point.MeTradePointFragment;
import ru.panmin.gtspro.ui.tredpoint.supervision_trade_point.SwTradePointFragment;

class TradePoinPagerAdapter extends FragmentPagerAdapter {

    private int COUNT = 2;
    private boolean b;
    private Fragment fragment;

    TradePoinPagerAdapter(FragmentManager supportFragmentManager, boolean b) {
        super(supportFragmentManager);
        this.b = b;
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
        switch (position) {
            case 0:
                fragment = b ?  MeTradePointFragment.createInstance() :  SwTradePointFragment.createInstance();

                return fragment;
            case 1:
                fragment =  MapFragment.createInstance();
                return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return COUNT;
    }


}
