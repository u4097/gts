package ru.panmin.gtspro.ui.report.me;

import ru.panmin.gtspro.data.models.FormOrReport;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface ReportMeMvpView extends ToolbarMvpView {

    void serReport(FormOrReport report);

}