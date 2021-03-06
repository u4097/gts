package ru.panmin.gtspro.ui.report.me;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.FormOrReport;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.questiongroups.QuestionGroupsActivity;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class ReportMeActivity extends ToolbarActivity implements ReportMeMvpView {

    private static final String INTENT_KEY_REPORT_ID = "report.id";

    @Inject ReportMePresenter reportMePresenter;

    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.btnResume) AppCompatButton btnResume;

    public ReportMeActivity() {
    }

    public static Intent getStartIntent(Context context, String reportId) {
        Intent intent = new Intent(context, ReportMeActivity.class);
        intent.putExtra(INTENT_KEY_REPORT_ID, reportId);
        return intent;
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_report_me;
    }


    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        reportMePresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
    }

    @Override
    protected void initViews() {
        reportMePresenter.getReportById(getIntent().getStringExtra(INTENT_KEY_REPORT_ID));
    }

    @Override
    protected void detachView() {
        reportMePresenter.detachView();
    }

    @Override
    public void serReport(FormOrReport report) {
        setTitle(report.getClient().getName().toString());
        tvDescription.setText(report.getName().toString());
        btnResume.setOnClickListener(view -> startActivity(QuestionGroupsActivity.getStartIntent(this, report.getId())));
        setStateData();
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