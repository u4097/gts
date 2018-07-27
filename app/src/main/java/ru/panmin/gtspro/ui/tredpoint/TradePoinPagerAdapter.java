package ru.panmin.gtspro.ui.tredpoint;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class TradePoinPagerAdapter extends FragmentPagerAdapter {

    private int COUNT = 2;


    TradePoinPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
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
        return null;
    }

    @Override
    public int getCount() {
        return COUNT;
    }


}
