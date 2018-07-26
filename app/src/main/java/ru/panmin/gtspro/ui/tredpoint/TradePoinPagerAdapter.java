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
        return super.getPageTitle(position);

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
