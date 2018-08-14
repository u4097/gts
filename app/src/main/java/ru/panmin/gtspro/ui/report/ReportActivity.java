package ru.panmin.gtspro.ui.report;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.FormOrReport;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class ReportActivity extends ToolbarActivity implements ReportMvpView {

    private static final String INTENT_KEY_REPORT_ID = "report.id";

    @Inject ReportPresenter reportPresenter;

    //@BindView(R.id.recyclerViewForms) RecyclerView recyclerViewForms;

    public ReportActivity() {
    }

    public static Intent getStartIntent(Context context, String reportId) {
        Intent intent = new Intent(context, ReportActivity.class);
        intent.putExtra(INTENT_KEY_REPORT_ID, reportId);
        return intent;
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_report;
    }


    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        reportPresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void detachView() {
        reportPresenter.detachView();
    }

    @Override
    public void serReport(FormOrReport report) {
        setTitle(report.getName().toString());
    }

    @Override
    protected EmptyBundle getEmptyBundle() {
        return null;
    }

    @Override
    protected void emptyButtonClick() {
    }

    @Override
    protected void errorButtonClick() {
    }

}