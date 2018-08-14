package ru.panmin.gtspro.ui.questiongroups;

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
import ru.panmin.gtspro.data.models.QuestionGroup;

public class QuestionGroupsAdapter extends RecyclerView.Adapter<QuestionGroupsAdapter.QuestionGroupViewHolder> {

    private List<QuestionGroup> questionGroups = new ArrayList<>();
    private OnQuestionGroupClickListener onQuestionGroupClickListener;

    @Inject
    QuestionGroupsAdapter() {
    }

    @NonNull
    @Override
    public QuestionGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_group, parent, false);
        return new QuestionGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionGroupViewHolder holder, int position) {
        holder.bind(questionGroups.get(position));
    }

    @Override
    public int getItemCount() {
        return (questionGroups == null || questionGroups.isEmpty()) ? 0 : questionGroups.size();
    }

    public void setData(List<QuestionGroup> questionGroups) {
        this.questionGroups.clear();
        this.questionGroups.addAll(questionGroups);
        notifyDataSetChanged();
    }

    public void setOnQuestionGroupClickListener(OnQuestionGroupClickListener onQuestionGroupClickListener) {
        this.onQuestionGroupClickListener = onQuestionGroupClickListener;
    }

    interface OnQuestionGroupClickListener {
        void onQuestionGroupClick(QuestionGroup questionGroup);
    }

    class QuestionGroupViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textQuestionGroupName) AppCompatTextView textQuestionGroupName;
        @BindView(R.id.textQuestionGroupCount) AppCompatTextView textQuestionGroupCount;
        @BindView(R.id.textQuestionGroupCountPlurals) AppCompatTextView textQuestionGroupCountPlurals;

        QuestionGroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("DefaultLocale")
        void bind(QuestionGroup questionGroup) {
            textQuestionGroupName.setText(questionGroup.getName().toString());
            textQuestionGroupCount.setText(String.format("%d", questionGroup.getQuestions().size()));
            textQuestionGroupCountPlurals.setText(
                    textQuestionGroupCountPlurals
                            .getContext()
                            .getResources()
                            .getQuantityString(
                                    R.plurals.question_plurals, questionGroup.getQuestions().size()
                            )
            );
            itemView.setOnClickListener(view -> onQuestionGroupClickListener.onQuestionGroupClick(questionGroup));
        }

    }

}