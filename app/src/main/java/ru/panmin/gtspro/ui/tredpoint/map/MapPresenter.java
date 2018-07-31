package ru.panmin.gtspro.ui.tredpoint.map;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;

public class MapPresenter extends ProgressPresenter<MapMvpView> {

    private final DataManager dataManager;

    @Inject
    MapPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {

    }
}
