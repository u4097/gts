package ru.panmin.gtspro.ui.main;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

public class MainPresenter extends ToolbarPresenter<MainMvpView> {

    private final DataManager dataManager;

    @Inject
    MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

}