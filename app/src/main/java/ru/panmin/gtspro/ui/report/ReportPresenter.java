package ru.panmin.gtspro.ui.report;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

public class ReportPresenter extends ToolbarPresenter<ReportMvpView> {

    private final DataManager dataManager;

    @Inject
    ReportPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    void getReportById(String reportId) {
        getMvpView().serReport(dataManager.getReportById(reportId));
    }

}