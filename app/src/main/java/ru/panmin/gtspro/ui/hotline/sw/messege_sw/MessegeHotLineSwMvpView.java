package ru.panmin.gtspro.ui.hotline.sw.messege_sw;

import ru.panmin.gtspro.data.models.Client;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

public interface MessegeHotLineSwMvpView extends ToolbarMvpView{
    void setClientSku(Client client);
}
