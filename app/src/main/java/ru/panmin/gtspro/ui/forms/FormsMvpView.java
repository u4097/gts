package ru.panmin.gtspro.ui.forms;

import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface FormsMvpView extends ToolbarMvpView {

    void setPromo(Promo promo);

}