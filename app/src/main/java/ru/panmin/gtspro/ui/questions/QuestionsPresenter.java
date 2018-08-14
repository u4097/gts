package ru.panmin.gtspro.ui.questions;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;

class QuestionsPresenter extends ToolbarPresenter<QuestionsMvpView> {

    private final DataManager dataManager;

    @Inject
    QuestionsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

}