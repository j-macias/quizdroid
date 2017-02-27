package edu.washington.jmacias.quizdroid;

import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TopicRepository {

    private static final String TAG = "TopicRepository";
    private static final int NUM_ANSWER_CHOICES = 4;
    private File extFilesDir;
    private ArrayList<Topic> topicList;

    public TopicRepository(File file) {
        extFilesDir = file;
        topicList = new ArrayList<>();

        try {
            topicList = readJsonStream();
        } catch(IOException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    private ArrayList<Topic> readJsonStream() throws IOException {
        JsonReader reader = null;
        try{
            reader = new JsonReader(new FileReader(extFilesDir + "/questions.json"));
        } catch(FileNotFoundException e) {
            Log.d(TAG, e.getMessage());
        }
        try {
            return readTopicsArray(reader);
        } finally {
            reader.close();
        }
    }

    private ArrayList<Topic> readTopicsArray(JsonReader reader) throws IOException {
        ArrayList<Topic> topics = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            topics.add(readTopic(reader));
        }
        reader.endArray();
        return topics;
    }

    private Topic readTopic(JsonReader reader) throws IOException {
        String title = null;
        String desc = null;
        ArrayList<Question> questions = new ArrayList<>();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("title")) {
                title = reader.nextString();
            } else if (name.equals("desc")) {
                desc = reader.nextString();
            } else if (name.equals("questions")) {
                questions = readQuestionsArray(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Topic(title, desc, desc, questions);
    }

    private ArrayList<Question> readQuestionsArray(JsonReader reader) throws IOException {
        ArrayList<Question> questions = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            questions.add(readQuestion(reader));
        }
        reader.endArray();
        return questions;
    }

    private Question readQuestion(JsonReader reader) throws IOException {
        String text = null;
        int answer = -1;
        String[] answers = new String[NUM_ANSWER_CHOICES];

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("text")) {
                text = reader.nextString();
            } else if (name.equals("answer")) {
                answer = reader.nextInt();
            } else if (name.equals("answers")) {
                answers = readAnswers(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Question(text, answers, answer - 1);
    }

    private String[] readAnswers(JsonReader reader) throws IOException {
        String[] answers = new String[NUM_ANSWER_CHOICES];

        reader.beginArray();
        for (int i = 0; i < NUM_ANSWER_CHOICES; i++) {
            answers[i] = reader.nextString();
        }
        reader.endArray();
        return answers;
    }

    String[] getTopicTitles() {
        String[] list = new String[topicList.size()];
        for(int i = 0; i < topicList.size(); i++) {
            list[i] = topicList.get(i).getTitle();
        }
        return list;
    }

    String[] getTopicShortDescriptions() {
        String[] list = new String[topicList.size()];
        for(int i = 0; i < topicList.size(); i++) {
            list[i] = topicList.get(i).getShortDescription();
        }
        return list;
    }

    String[] getTopicLongDescriptions() {
        String[] list = new String[topicList.size()];
        for(int i = 0; i < topicList.size(); i++) {
            list[i] = topicList.get(i).getLongDescription();
        }
        return list;
    }

    String[] getTopicListText() {
        String[] list = new String[topicList.size()];
        String[] titles = getTopicTitles();
        String[] shortDescriptions = getTopicShortDescriptions();
        for (int i = 0; i < topicList.size(); i++) {
            list[i] = titles[i] + ": " + shortDescriptions[i];
        }
        return list;
    }

    ArrayList<Topic> getTopicList() {
        return topicList;
    }
}
