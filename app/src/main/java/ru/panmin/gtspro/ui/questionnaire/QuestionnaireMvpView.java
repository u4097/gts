package ru.panmin.gtspro.ui.questionnaire;

import java.util.List;

import ru.panmin.gtspro.data.models.Question;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface QuestionnaireMvpView extends ToolbarMvpView {

    void setQuestions(List<Question> questions);

}