package edu.washington.jmacias.quizdroid;

import java.util.ArrayList;

public class TopicRepository {

    private ArrayList<Topic> topicList;

    public TopicRepository() {
        topicList = new ArrayList<>();
        final Question mathQ1 = new Question(
                "What is 2 + 2?",
                new String[] {"22", "5, for extremely large values of 2", "4", "Fish"},
                2);
        final Question mathQ2 = new Question(
                "What is 7 x 9?",
                new String[] {"79", "63", "4", "54"},
                1);
        final Question mathQ3 = new Question(
                "What is the derivative of 2x^3?",
                new String[] {"6x^3", "6x^2", "(2x^2)/3", "3x^2"},
                1);
        final Question mathQ4 = new Question(
                "What is the derivative of 7?",
                new String[] {"1", "7x", "7x^2", "0"},
                3);
        final Topic mathTopic = new Topic(
                "Math",
                "Math Q's",
                "Questions on math (4 questions)",
                new ArrayList<Question>(){{
                    add(mathQ1);
                    add(mathQ2);
                    add(mathQ3);
                    add(mathQ4);}}
        );
        topicList.add(mathTopic);

        final Question physQ1 = new Question(
                "What is the speed of gravity on Earth?",
                new String[] {"9.81 m/s^2", "-9.81 m/s^2", "8.72 m//s^2", "It has a speed?"},
                0);
        final Question physQ2 = new Question(
                "Which items falls fastest, assuming they are the same size?",
                new String[] {"A 1 pound weight", "A 5 pound weight", "A 2000 pound weight",
                        "They all fall at the same speed"},
                3);
        final Question physQ3 = new Question(
                "Which planet has the longest day (time to rotate about its axis)?",
                new String[] {"Earth", "Mars", "Neptune", "Jupiter"},
                1);
        final Topic physicsTopic = new Topic(
                "Physics",
                "Phys Q's",
                "Questions on physics (3 questions)",
                new ArrayList<Question>(){{
                    add(physQ1);
                    add(physQ2);
                    add(physQ3);}}
        );
        topicList.add(physicsTopic);

        final Question marvelQ1 = new Question(
                "Who of these superheroes were introduced first?",
                new String[] {"Captain America", "Iron Man", "The Incredible Hulk", "Wolverine"},
                0);
        final Question marvelQ2 = new Question(
                "Which superhero was featured in 2012's movie 'The Avengers'?",
                new String[] {"Iron Man", "Thor", "Captain America", "All of the above"},
                3);
        final Question marvelQ3 = new Question(
                "When did the Black Widow make her debut?",
                new String[] {"1956", "1959", "1962", "1964"},
                3);
        final Topic marvelTopic = new Topic(
                "Marvel Super Heroes",
                "Marvel Q's",
                "Questions on Marvel super heroes (3 questions)",
                new ArrayList<Question>(){{
                    add(marvelQ1);
                    add(marvelQ2);
                    add(marvelQ3);}}
        );
        topicList.add(marvelTopic);
    }

    public String[] getTopicTitles() {
        String[] list = new String[topicList.size()];
        for(int i = 0; i < topicList.size(); i++) {
            list[i] = topicList.get(i).getTitle();
        }
        return list;
    }

    public String[] getTopicShortDescriptions() {
        String[] list = new String[topicList.size()];
        for(int i = 0; i < topicList.size(); i++) {
            list[i] = topicList.get(i).getShortDescription();
        }
        return list;
    }

    public String[] getTopicLongDescriptions() {
        String[] list = new String[topicList.size()];
        for(int i = 0; i < topicList.size(); i++) {
            list[i] = topicList.get(i).getLongDescription();
        }
        return list;
    }

    public String[] getTopicListText() {
        String[] list = new String[topicList.size()];
        String[] titles = getTopicTitles();
        String[] shortDescriptions = getTopicShortDescriptions();
        for (int i = 0; i < topicList.size(); i++) {
            list[i] = titles[i] + ": " + shortDescriptions[i];
        }
        return list;
    }

    public ArrayList<Topic> getTopicList() {
        return topicList;
    }
}
