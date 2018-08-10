package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.panmin.gtspro.data.models.SkuListElement;
import ru.panmin.gtspro.ui.base.BaseFragment;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_all_sku.ChoiceSkuAllListFragment;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_grop_sku.ChoiceSkuGroupListFragment;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_selected_sku.SelectedSkuListFragment;

public class SkuChoicePagerAdapter extends FragmentPagerAdapter {

    public static final int PAGE_GROUP = 0;
    public static final int PAGE_LIST = 1;
    public static final int PAGE_SELECTED = 2;

    private static final int COUNT = 3;

    private List<BaseSelectSkuInterface> interfaces = new ArrayList<>();

    public SkuChoicePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position) {
            case PAGE_GROUP:
                title = "По группам";
                break;
            case PAGE_LIST:
                title = "Все";
                break;
            case PAGE_SELECTED:
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
            case PAGE_GROUP:
                fragment = ChoiceSkuGroupListFragment.createInstance();
                break;
            case PAGE_LIST:
                fragment = ChoiceSkuAllListFragment.createInstance();
                break;
            case PAGE_SELECTED:
                fragment = SelectedSkuListFragment.createInstance();
                break;
        }
        interfaces.add((BaseSelectSkuInterface) fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    public void selectSku(int page, SkuListElement skuListElement) {
        for (int i = 0, interfacesSize = interfaces.size(); i < interfacesSize; i++) {
            if (page != i) {
                BaseSelectSkuInterface skuInterface = interfaces.get(i);
                skuInterface.selectSku(i, skuListElement);
            }
        }
    }

    public void deselectSku(int page, SkuListElement skuListElement) {
        for (int i = 0, interfacesSize = interfaces.size(); i < interfacesSize; i++) {
            if (page != i) {
                BaseSelectSkuInterface skuInterface = interfaces.get(i);
                skuInterface.deselectSku(i, skuListElement);
            }
        }
    }

}
