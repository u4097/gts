package ru.panmin.gtspro.ui.tradepointinfo.sv.merchandiser;

import ru.panmin.gtspro.data.models.Merchandiser;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface MerchandiserMvpView extends ToolbarMvpView {

    void setMerchandiser(Merchandiser merchandiser);

}