package com.example.mobileapplicationsproject.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Answer implements Comparable, Serializable {
    private String userAnswer;
    private boolean isCorrect;
    private String questionString;

    public Answer(String userAnswer, String questionString, boolean isCorrect) {
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
        this.questionString = questionString;
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Answer otherObject = (Answer) o;
        if (!this.isCorrect() && otherObject.isCorrect()) {
            return -1;
        } else if (this.isCorrect == otherObject.isCorrect) {
            return 0;
        } else return 1;
    }

    @Override
    public String toString() {
        String resultString;
        if (isCorrect) resultString = "Right";
        else resultString = "Wrong";
        return "Question: " + questionString + " Answer: " + userAnswer + " Result: " + resultString;
    }
}
