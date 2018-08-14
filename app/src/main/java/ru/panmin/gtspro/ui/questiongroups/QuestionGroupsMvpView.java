package ru.panmin.gtspro.ui.questiongroups;

import java.util.List;

import ru.panmin.gtspro.data.models.QuestionGroup;
import ru.panmin.gtspro.ui.toolbar.ToolbarMvpView;

interface QuestionGroupsMvpView extends ToolbarMvpView {

    void setQuestionGroups(String formName, List<QuestionGroup> questionGroups);

}