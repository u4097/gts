package ru.panmin.gtspro.ui.hotline.sw.messege_sw;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

public class MessegeHotLineSwPresenter extends ToolbarPresenter<MessegeHotLineSwMvpView> {

    private final DataManager dataManager;

    @Inject
    MessegeHotLineSwPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {

    }
}
