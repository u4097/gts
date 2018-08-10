package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.panmin.gtspro.ui.base.BaseFragment;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_all_sku.ChoiceSkuAllListFragment;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_grop_sku.ChoiceSkuGroupListFragment;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.fragment_selected_sku.SelectedSkuListFragment;

class SkuChoicePagerAdapter extends FragmentPagerAdapter {

    private static final int COUNT = 3;


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
                fragment = ChoiceSkuGroupListFragment.createInstance();
                break;
            case 1:
                fragment = ChoiceSkuAllListFragment.createInstance();
                break;
            case 2:
                fragment = SelectedSkuListFragment.createInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

}
