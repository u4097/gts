package ru.panmin.gtspro.ui.blocks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.FormOrReport;
import ru.panmin.gtspro.ui.customviews.VectorsSupportTextView;

public class ReportSvAdapter extends RecyclerView.Adapter<ReportSvAdapter.ReportViewHolder> {

    private List<FormOrReport> reports = new ArrayList<>();
    private OnReportClickListener onReportClickListener;

    @Inject
    public ReportSvAdapter() {
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report_sv, parent, false);
        return new ReportViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        holder.bind(reports.get(position));
    }

    @Override
    public int getItemCount() {
        return (reports == null || reports.isEmpty()) ? 0 : reports.size();
    }

    public void setOnReportClickListener(OnReportClickListener onReportClickListener) {
        this.onReportClickListener = onReportClickListener;
    }

    public void setData(List<FormOrReport> reports) {
        this.reports.clear();
        this.reports.addAll(reports);
        notifyDataSetChanged();
    }

    public interface OnReportClickListener {
        void onReportClick(FormOrReport report);
    }

    class ReportViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textReportName) AppCompatTextView textReportName;
        @BindView(R.id.textFilledInWithPercent) AppCompatTextView textFilledInWithPercent;
        @BindView(R.id.textDateCreate) VectorsSupportTextView textDateCreate;
        @BindView(R.id.textDateCompletion) VectorsSupportTextView textDateCompletion;
        @BindView(R.id.textNowPerformName) AppCompatTextView textNowPerformName;


        public ReportViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(FormOrReport report) {
            textReportName.setText(report.getName().toString());
            textFilledInWithPercent.setText(String.format("%s%%", report.getFilledPercent()));
            textDateCreate.setText(report.getDateBeginWithFormat());
            textDateCompletion.setText(report.getDateFinishWithFormat());
            textNowPerformName.setText(report.getClient() == null ? "" : report.getClient().getName().toString());
            itemView.setOnClickListener(view -> onReportClickListener.onReportClick(report));
        }

    }

}