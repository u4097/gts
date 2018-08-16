package ru.panmin.gtspro.ui.questionnaire;

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
import ru.panmin.gtspro.data.models.Question;

public class QuestionnaireAdapter extends RecyclerView.Adapter<QuestionnaireAdapter.BaseQuestionViewHolder> {

    private List<Question> questions = new ArrayList<>();
    private AnswerQuestionListener answerQuestionListener;

    @Inject
    QuestionnaireAdapter() {
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @NonNull
    @Override
    public BaseQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case 1:
                view = layoutInflater.inflate(R.layout.item_question_type_1, parent, false);
                return new QuestionType1ViewHolder(view);
            case 2:
                view = layoutInflater.inflate(R.layout.item_question_type_2, parent, false);
                return new QuestionType2ViewHolder(view);
            case 3:
                view = layoutInflater.inflate(R.layout.item_question_type_3, parent, false);
                return new QuestionType3ViewHolder(view);
            case 4:
                view = layoutInflater.inflate(R.layout.item_question_type_4, parent, false);
                return new QuestionType4ViewHolder(view);
            case 5:
                view = layoutInflater.inflate(R.layout.item_question_type_5, parent, false);
                return new QuestionType5ViewHolder(view);
            case 6:
                view = layoutInflater.inflate(R.layout.item_question_type_6, parent, false);
                return new QuestionType6ViewHolder(view);
            case 7:
                view = layoutInflater.inflate(R.layout.item_question_type_7, parent, false);
                return new QuestionType7ViewHolder(view);
            case 8:
                view = layoutInflater.inflate(R.layout.item_question_type_8, parent, false);
                return new QuestionType8ViewHolder(view);
            default:
                view = layoutInflater.inflate(R.layout.item_question_type_1, parent, false);
                return new QuestionType1ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseQuestionViewHolder holder, int position) {
        ((BaseQuestionViewHolder) holder).bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return (questions == null || questions.isEmpty()) ? 0 : questions.size();
    }

    public void setData(List<Question> questions) {
        this.questions.clear();
        this.questions.addAll(questions);
        notifyDataSetChanged();
    }

    private Question getItem(int position) {
        return questions.get(position);
    }

    public void setAnswerQuestionListener(AnswerQuestionListener answerQuestionListener) {
        this.answerQuestionListener = answerQuestionListener;
    }

    interface AnswerQuestionListener {
        void answerQuestion(Question question);
    }

    abstract class BaseQuestionViewHolder extends RecyclerView.ViewHolder {

        BaseQuestionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        abstract void bind(Question question);

    }

    class QuestionType1ViewHolder extends BaseQuestionViewHolder {

        QuestionType1ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(Question question) {
        }

    }

    class QuestionType2ViewHolder extends BaseQuestionViewHolder {

        QuestionType2ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(Question question) {
        }

    }

    class QuestionType3ViewHolder extends BaseQuestionViewHolder {

        QuestionType3ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(Question question) {
        }

    }

    class QuestionType4ViewHolder extends BaseQuestionViewHolder {

        QuestionType4ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(Question question) {
        }

    }

    class QuestionType5ViewHolder extends BaseQuestionViewHolder {

        QuestionType5ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(Question question) {
        }

    }

    class QuestionType6ViewHolder extends BaseQuestionViewHolder {

        QuestionType6ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(Question question) {
        }

    }

    class QuestionType7ViewHolder extends BaseQuestionViewHolder {

        QuestionType7ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(Question question) {
        }

    }

    class QuestionType8ViewHolder extends BaseQuestionViewHolder {

        QuestionType8ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(Question question) {
        }

    }

}