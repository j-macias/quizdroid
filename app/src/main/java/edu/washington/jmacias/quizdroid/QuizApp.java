package edu.washington.jmacias.quizdroid;

import android.util.Log;

public class QuizApp extends android.app.Application{
    private TopicRepository instance = new TopicRepository();

    public QuizApp() {
        Log.d("QuizApp", "QuizApp constructor fired");
    }

    @Override
    public void onCreate() {
        Log.d("QuizApp", "QuizApp onCreate fired");
    }

    public TopicRepository getRepository() {
        return instance;
    }
}
