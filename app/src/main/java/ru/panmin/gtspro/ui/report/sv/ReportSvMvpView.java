package ru.panmin.gtspro.ui.report.sv;

import ru.panmin.gtspro.data.models.FormOrReport;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface ReportSvMvpView extends ToolbarMvpView {

    void serReport(FormOrReport report);

}