package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.panmin.gtspro.ui.base.BaseFragment;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_sku.ChoiceSkuListFragment;

class SkuChoicePagerAdapter extends FragmentPagerAdapter {

    private static final int COUNT = 3;
    private static final int GROUP_SKU = 0;
    private static final int ALL_SKU = 1;
    private static final int CHOICE_SKU = 2;

    public SkuChoicePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position) {
            case 0:
                title = "По группам";
                break;
            case 1:
                title = "Все";
                break;
            case 2:
                title = "Выбраннын";
                break;
            default:
                title = "";
                break;
        }
        return title;
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment = null;
        switch (position) {
            case 0:
                fragment = ChoiceSkuListFragment.createInstance();
                break;
            case 1:
                fragment = ChoiceSkuListFragment.createInstance();
                break;
            case 2:
                fragment = ChoiceSkuListFragment.createInstance();
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

}
