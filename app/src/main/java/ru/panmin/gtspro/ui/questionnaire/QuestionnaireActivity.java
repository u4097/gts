package ru.panmin.gtspro.ui.questionnaire;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Question;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import ru.panmin.gtspro.utils.OtherUtils;
import ru.panmin.gtspro.utils.SpaceItemDecoration;

public class QuestionnaireActivity
        extends ToolbarActivity
        implements QuestionnaireMvpView,
        QuestionnaireAdapter.AnswerQuestionListener {

    private static final String INTENT_KEY_QUESTION_IDS = "question.ids";

    @Inject QuestionnairePresenter questionnairePresenter;
    @Inject QuestionnaireAdapter questionnaireAdapter;

    @BindView(R.id.recyclerViewQuestionnaire) RecyclerView recyclerViewQuestionnaire;

    public QuestionnaireActivity() {
    }

    public static Intent getStartIntent(Context context, List<String> questionIds) {
        Intent intent = new Intent(context, QuestionnaireActivity.class);
        intent.putStringArrayListExtra(INTENT_KEY_QUESTION_IDS, new ArrayList<>(questionIds));
        return intent;
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_questionnaire;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        questionnairePresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
    }

    @Override
    protected void initViews() {
        recyclerViewQuestionnaire.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewQuestionnaire.addItemDecoration(new SpaceItemDecoration(OtherUtils.dpToPx(8)));
        questionnaireAdapter.setAnswerQuestionListener(this);
        recyclerViewQuestionnaire.setAdapter(questionnaireAdapter);

        questionnairePresenter.getQuestionsByIds(getIntent().getStringArrayListExtra(INTENT_KEY_QUESTION_IDS));
    }

    @Override
    protected void detachView() {
        questionnairePresenter.detachView();
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

    @Override
    public void setQuestions(List<Question> questions) {
        if (!questions.isEmpty()) {
            setTitle(questions.get(0).getGroupName().toString());
        }
        questionnaireAdapter.setData(questions);
        setStateData();
    }

    @Override
    public void answerQuestion(Question question) {
    }

}