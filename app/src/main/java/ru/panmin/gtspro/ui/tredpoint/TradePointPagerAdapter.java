package ru.panmin.gtspro.ui.tredpoint;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.panmin.gtspro.ui.tredpoint.map.MapFragment;
import ru.panmin.gtspro.ui.tredpoint.merchandise_trade_point.MeTradePointFragment;
import ru.panmin.gtspro.ui.tredpoint.supervision_trade_point.SwTradePointFragment;

class TradePointPagerAdapter extends FragmentPagerAdapter {

    private static final int COUNT = 2;
    private final boolean isSupervisor;

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
        switch (position) {
            case 0:
                return isSupervisor ? SwTradePointFragment.createInstance() : MeTradePointFragment.createInstance();
            case 1:
                return MapFragment.createInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

}