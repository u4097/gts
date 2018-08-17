package ru.panmin.gtspro.ui.questionnaire;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.models.Question;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class QuestionnairePresenter extends ToolbarPresenter<QuestionnaireMvpView> {

    private final DataManager dataManager;


    @Inject
    QuestionnairePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getQuestionsByIds(List<String> questionsIds) {
        List<Question> questions = new ArrayList<>();
        for (String questionId : questionsIds) {
            Question question = dataManager.getQuestionById(questionId);
            if (question != null) {
                questions.add(question);
            }
        }
        getMvpView().setQuestions(questions);
    }

}