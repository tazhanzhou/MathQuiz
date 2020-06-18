package com.example.mobileapplicationsproject.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private String score;
    private ArrayList<Question> questions;
    private ArrayList<Answer> answers;

    public User(ArrayList<Question> questions, ArrayList<Answer> answers) {
        this.questions = questions;
        this.answers = answers;
        this.score = calculateScore(questions,answers);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public String calculateScore(ArrayList<Question> questions, ArrayList<Answer> answers){
        answers = this.answers;
        questions = this.questions;

        int rightAnswersQty = 0;
        int allQuestionsQty=questions.size();

        for(Answer a : answers){
            if(a.isCorrect()==true){
                rightAnswersQty++;
            }
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float)rightAnswersQty/(float)allQuestionsQty*100);
        return result;
    }


}
