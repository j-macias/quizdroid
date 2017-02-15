package edu.washington.jmacias.quizdroid;

public class Question {

    private String questionText;
    private String[] answers;
    private int correctIndex;

    public Question(String questionText, String[] answers, int correctIndex) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctIndex = correctIndex;
    }

    public void setQuestionText(String newQuestionText) {
        questionText = newQuestionText;
    }

    public void setAnswers(String[] newAnswers) {
        answers = newAnswers;
    }

    public void setCorrectIndex(int newCorrectIndex) {
        correctIndex = newCorrectIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }
}
