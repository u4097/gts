package ru.panmin.gtspro.ui.report;

import ru.panmin.gtspro.data.models.FormOrReport;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface ReportMvpView extends ToolbarMvpView {

    void serReport(FormOrReport report);

}