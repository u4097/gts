package ru.panmin.gtspro.ui.report.sv;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class ReportSvPresenter extends ToolbarPresenter<ReportSvMvpView> {

    private final DataManager dataManager;

    @Inject
    ReportSvPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    void getReportById(String reportId) {
        getMvpView().serReport(dataManager.getReportById(reportId));
    }

}