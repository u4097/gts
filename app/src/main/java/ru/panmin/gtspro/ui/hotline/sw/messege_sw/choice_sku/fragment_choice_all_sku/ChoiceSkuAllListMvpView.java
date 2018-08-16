package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_all_sku;

import android.util.ArraySet;

import ru.panmin.gtspro.data.models.SkuListElement;
import ru.panmin.gtspro.ui.progress.ProgressMvpView;

public interface ChoiceSkuAllListMvpView extends ProgressMvpView {
    void showData(ArraySet<SkuListElement> skuListElements);
}
