package ru.panmin.gtspro.ui.blocks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.FormOrReport;

public class ReportMeAdapter extends RecyclerView.Adapter<ReportMeAdapter.ReportViewHolder> {

    private List<FormOrReport> reports = new ArrayList<>();
    private OnReportClickListener onReportClickListener;

    @Inject
    public ReportMeAdapter() {
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report_me, parent, false);
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

        public ReportViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(FormOrReport report) {
            itemView.setOnClickListener(view -> onReportClickListener.onReportClick(report));
        }

    }

}