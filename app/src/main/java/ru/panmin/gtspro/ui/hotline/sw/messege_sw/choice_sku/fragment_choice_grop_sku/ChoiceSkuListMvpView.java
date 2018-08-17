package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_grop_sku;

import java.util.HashMap;
import java.util.List;

import ru.panmin.gtspro.data.models.Group;
import ru.panmin.gtspro.data.models.SkuForAdapter;
import ru.panmin.gtspro.ui.progress.ProgressMvpView;

public interface ChoiceSkuListMvpView extends ProgressMvpView {

    void showData(HashMap<Group, List<SkuForAdapter>> sort);

}
