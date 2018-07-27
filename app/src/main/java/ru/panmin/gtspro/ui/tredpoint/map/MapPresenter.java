package ru.panmin.gtspro.ui.tredpoint.map;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

public class MapPresenter extends ToolbarPresenter<MapMvpView> {

    private final DataManager dataManager;

    @Inject
    MapPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {

    }
}
