package edu.washington.jmacias.quizdroid;

import android.util.Log;

public class QuizApp extends android.app.Application{

    private static QuizApp singleton;
    private TopicRepository instance;
    private String URL;
    private int downloadInterval;
    private static final String DEFAULT_URL = "http://tednewardsandbox.site44.com/questions.json";
    public static final int DEFAULT_INTERVAL = 10;

    public QuizApp() {
        Log.d("QuizApp", "QuizApp constructor fired");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("QuizApp", "QuizApp onCreate fired");
        singleton = this;
        URL = DEFAULT_URL;
        downloadInterval = DEFAULT_INTERVAL;
        Log.d("QuizApp", "Checking file location " + getExternalFilesDir(null));
        try {
            instance = new TopicRepository(getExternalFilesDir(null));
        } catch(NullPointerException e) {
            Log.d("QuizApp", "File not found");
        }
    }

    public TopicRepository getRepository() {
        return instance;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public void setDownloadInterval(int interval) {
        this.downloadInterval = interval;
    }

    public int getDownloadInterval() {
        return downloadInterval;
    }

    private static QuizApp getInstance() {
        return singleton;
    }
}
