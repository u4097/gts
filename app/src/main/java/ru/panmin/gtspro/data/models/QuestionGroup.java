package ru.panmin.gtspro.data.models;

import java.util.ArrayList;
import java.util.List;

public class QuestionGroup {

    private Name name;
    private List<Question> questions = new ArrayList<>();

    public QuestionGroup(Question question) {
        this.name = question.getGroupName();
        questions.add(question);
    }

    public QuestionGroup(List<Question> questions) {
        this.name = questions.get(0).getGroupName();
        this.questions = questions;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

}