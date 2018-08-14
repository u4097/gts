package ru.panmin.gtspro.ui.questiongroups;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.QuestionGroup;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import ru.panmin.gtspro.utils.OtherUtils;
import ru.panmin.gtspro.utils.SpaceItemDecoration;

public class QuestionGroupsActivity
        extends ToolbarActivity
        implements QuestionGroupsMvpView,
        QuestionGroupsAdapter.OnQuestionGroupClickListener {

    private static final String INTENT_KEY_FORM_ID = "form.id";

    @Inject QuestionGroupsPresenter questionGroupsPresenter;
    @Inject QuestionGroupsAdapter questionGroupsAdapter;

    @BindView(R.id.recyclerViewQuestionGroups) RecyclerView recyclerViewQuestionGroups;

    public QuestionGroupsActivity() {
    }

    public static Intent getStartIntent(Context context, String formId) {
        Intent intent = new Intent(context, QuestionGroupsActivity.class);
        intent.putExtra(INTENT_KEY_FORM_ID, formId);
        return intent;
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_question_groups;
    }


    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        questionGroupsPresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
    }

    @Override
    protected void initViews() {
        recyclerViewQuestionGroups.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewQuestionGroups.addItemDecoration(new SpaceItemDecoration(OtherUtils.dpToPx(8)));
        questionGroupsAdapter.setOnQuestionGroupClickListener(this);
        recyclerViewQuestionGroups.setAdapter(questionGroupsAdapter);

        questionGroupsPresenter.getForm(getIntent().getStringExtra(INTENT_KEY_FORM_ID));
    }

    @Override
    protected void detachView() {
        questionGroupsPresenter.detachView();
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
    public void setQuestionGroups(String formName, List<QuestionGroup> questionGroups) {
        setTitle(formName);
        questionGroupsAdapter.setData(questionGroups);
        setStateData();
    }

    @Override
    public void onQuestionGroupClick(QuestionGroup questionGroup) {
    }

}