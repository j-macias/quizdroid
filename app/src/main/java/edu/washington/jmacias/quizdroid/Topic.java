package edu.washington.jmacias.quizdroid;

import java.util.ArrayList;

public class Topic {

    private String title;
    private String shortDescription;
    private String longDescription;
    private ArrayList<Question> questionList;

    public Topic(String title, String shortDescription, String longDescription,
                 ArrayList<Question> questionList) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.questionList = questionList;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setShortDescription(String newShortDescription) {
        shortDescription = newShortDescription;
    }

    public void setLongDescription(String newLongDescription) {
        longDescription = newLongDescription;
    }

    public void addQuestion(Question newQuestion) {
        questionList.add(newQuestion);
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public int getQuestionCount() {
        return questionList.size();
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }
}
