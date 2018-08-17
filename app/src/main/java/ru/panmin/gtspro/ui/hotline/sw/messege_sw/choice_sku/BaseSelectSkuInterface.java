package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku;

import ru.panmin.gtspro.data.models.SkuListElement;

public interface BaseSelectSkuInterface {

    void selectSku(int fromAction, SkuListElement skuListElement);

    void deselectSku(int fromAction, SkuListElement skuListElement);

}