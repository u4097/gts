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

public class PhotoReportSvAdapter extends RecyclerView.Adapter<PhotoReportSvAdapter.PhotoReportViewHolder> {

    private List<FormOrReport> photoReports = new ArrayList<>();
    private OnPhotoReportClickListener onPhotoReportClickListener;

    @Inject
    public PhotoReportSvAdapter() {
    }

    @NonNull
    @Override
    public PhotoReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_report_sv, parent, false);
        return new PhotoReportViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoReportViewHolder holder, int position) {
        holder.bind(photoReports.get(position));
    }

    @Override
    public int getItemCount() {
        return (photoReports == null || photoReports.isEmpty()) ? 0 : photoReports.size();
    }

    public void setOnPhotoReportClickListener(OnPhotoReportClickListener onPhotoReportClickListener) {
        this.onPhotoReportClickListener = onPhotoReportClickListener;
    }

    public void setData(List<FormOrReport> photoReports) {
        this.photoReports.clear();
        this.photoReports.addAll(photoReports);
        notifyDataSetChanged();
    }

    public interface OnPhotoReportClickListener {
        void onPhotoReportClick(FormOrReport photoReport);
    }

    class PhotoReportViewHolder extends RecyclerView.ViewHolder {

        public PhotoReportViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(FormOrReport photoReport) {
            itemView.setOnClickListener(view -> onPhotoReportClickListener.onPhotoReportClick(photoReport));
        }

    }

}