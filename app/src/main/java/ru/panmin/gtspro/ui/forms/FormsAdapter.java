package ru.panmin.gtspro.ui.forms;

import android.annotation.SuppressLint;
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

public class FormsAdapter extends RecyclerView.Adapter<FormsAdapter.FormViewHolder> {

    private List<FormOrReport> formOrReports = new ArrayList<>();
    private OnFormClickListener onFormClickListener;

    @Inject
    FormsAdapter() {
    }

    @NonNull
    @Override
    public FormViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_form, parent, false);
        return new FormViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormViewHolder holder, int position) {
        holder.bind(formOrReports.get(position));
    }

    @Override
    public int getItemCount() {
        return (formOrReports == null || formOrReports.isEmpty()) ? 0 : formOrReports.size();
    }

    public void setData(List<FormOrReport> formOrReports) {
        this.formOrReports.clear();
        this.formOrReports.addAll(formOrReports);
        notifyDataSetChanged();
    }

    public void setOnFormClickListener(OnFormClickListener onFormClickListener) {
        this.onFormClickListener = onFormClickListener;
    }

    interface OnFormClickListener {
        void onFormClick(FormOrReport formOrReport);
    }

    class FormViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textFormName) AppCompatTextView textFormName;
        @BindView(R.id.textFormCount) AppCompatTextView textFormCount;
        @BindView(R.id.textFormCountPlurals) AppCompatTextView textFormCountPlurals;

        FormViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint({"SetTextI18n", "DefaultLocale"})
        void bind(FormOrReport formOrReport) {
            textFormName.setText(formOrReport.getName().toString());
            textFormCount.setText(String.format("%d", formOrReport.getQuestions().size()));
            textFormCountPlurals.setText(
                    textFormCountPlurals
                            .getContext()
                            .getResources()
                            .getQuantityString(
                                    R.plurals.question_plurals, formOrReport.getQuestions().size()
                            )
            );
            itemView.setOnClickListener(view -> onFormClickListener.onFormClick(formOrReport));
        }

    }

}