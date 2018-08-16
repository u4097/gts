package ru.panmin.gtspro.ui.questiongroups;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.RealmList;
import io.realm.Sort;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.models.FormOrReport;
import ru.panmin.gtspro.data.models.Question;
import ru.panmin.gtspro.data.models.QuestionGroup;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;
import ru.panmin.gtspro.utils.TextUtils;

class QuestionGroupsPresenter extends ToolbarPresenter<QuestionGroupsMvpView> {

    private final DataManager dataManager;

    @Inject
    QuestionGroupsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getForm(String formId) {
        FormOrReport formOrReport = dataManager.getFormById(formId);
        if (formOrReport != null) {
            List<QuestionGroup> questionGroups = new ArrayList<>();
            RealmList<Question> questions = formOrReport.getQuestions();
            questions.sort("groupType", Sort.ASCENDING);
            for (Question question : questions) {
                boolean isFindQuestionGroup = false;
                for (QuestionGroup questionGroupInList : questionGroups) {
                    if (TextUtils.equals(questionGroupInList.getName().toString(), question.getGroupName().toString())) {
                        isFindQuestionGroup = true;
                        questionGroupInList.addQuestion(question);
                        break;
                    }
                }
                if (!isFindQuestionGroup) {
                    questionGroups.add(new QuestionGroup(question));
                }
            }
            getMvpView().setQuestionGroups(formOrReport.getName().toString(), questionGroups);
        }
    }

}