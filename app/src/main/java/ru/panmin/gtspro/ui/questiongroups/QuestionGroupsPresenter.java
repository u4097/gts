package ru.panmin.gtspro.ui.questiongroups;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.RealmList;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.models.Form;
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
        Form form = dataManager.getFormById(formId);
        if (form != null) {
            List<QuestionGroup> questionGroups = new ArrayList<>();
            RealmList<Question> questions = form.getQuestions();
            questions.sort("groupType");
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
            getMvpView().setQuestionGroups(form.getName().toString(), questionGroups);
        }
    }

}