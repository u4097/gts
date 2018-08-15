package ru.panmin.gtspro.ui.report.me;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class ReportMePresenter extends ToolbarPresenter<ReportMeMvpView> {

    private final DataManager dataManager;

    @Inject
    ReportMePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    void getReportById(String reportId) {
        getMvpView().serReport(dataManager.getReportById(reportId));
    }

}