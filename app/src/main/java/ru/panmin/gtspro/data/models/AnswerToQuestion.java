package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

public class AnswerToQuestion {

    @SerializedName("visit_id") private String visitId;
    @SerializedName("question_id") private String questionId;
    @SerializedName("answer") private Answer answer;

    public AnswerToQuestion() {
    }

    public AnswerToQuestion(String visitId, String questionId, Answer answer) {
        this.visitId = visitId;
        this.questionId = questionId;
        this.answer = answer;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

}