package edu.washington.jmacias.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    public static final String TAG = "QuestionActivity";
    public static final String MESSAGE = "edu.washington.jmacias.quizdroid.QuestionActivity";
    private int questionNumber;
    private int questionsCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        final int topicIndex = getIntent().getExtras().getInt(TopicOverviewActivity.MESSAGE);
        final String[] topics = getResources().getStringArray(R.array.topics);

        TextView headerText = (TextView) findViewById(R.id.questionHeader);
        headerText.setText(topics[topicIndex] + " Question " + questionNumber + ":");
        TextView questionText = (TextView) findViewById(R.id.questionText);
        questionText.setText(questionsOptions[topicIndex][questionNumber - 1][0]);
        final RadioGroup optionRadioGroup = (RadioGroup) findViewById(R.id.answerOptions);
        for (int i = 0; i < optionRadioGroup.getChildCount(); i++) {
            ((RadioButton) optionRadioGroup.getChildAt(i)).setText(questionsOptions[topicIndex]
                    [questionNumber - 1][i + 1]);
        }

        final Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answerID = optionRadioGroup.getCheckedRadioButtonId();
                if (answerID > -1) {
                    Intent intent = new Intent(QuestionActivity.this, AnswerActivity.class);
                    intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.putExtra("topicIndex", topicIndex);
                    intent.putExtra("questionNumber", questionNumber);
                    intent.putExtra("questionsCorrect", questionsCorrect);
                    intent.putExtra("answerText", questionsOptions[topicIndex][questionNumber - 1]
                            [optionRadioGroup.getCheckedRadioButtonId() + 1]);
                    questionNumber++;
                    startActivity(intent);
                }
            }
        });
    }

    //TODO: fix this gross data structure in to XML file
    private String[][][] questionsOptions = {
            {
                    {
                            "What is 2 + 2?",
                            "22",
                            "5, for extremely large values of 2",
                            "4",
                            "Fish"
                    },
                    {
                            "What is 7 x 9?",
                            "79",
                            "63",
                            "4",
                            "54"
                    },
                    {
                            "What is the derivative of 2x^3?",
                            "6x^3",
                            "6x^2",
                            "(2x^2)/3",
                            "3x^2"
                    },
                    {
                            "What is the derivative of 7?",
                            "1",
                            "7x",
                            "7x^2",
                            "0"
                    }
            },
            {
                    {
                            "What is the speed of gravity on Earth?",
                            "9.81 m/s^2",
                            "-9.81 m/s^2",
                            "8.72 m//s^2",
                            "It has a speed?"
                    },
                    {
                            "Which items falls fastest, assuming they are the same size?",
                            "A 1 pound weight",
                            "A 5 pound weight",
                            "A 2000 pound weight",
                            "They all fall at the same speed"
                    },
                    {
                            "Which planet has the longest day (time to rotate about its axis)?",
                            "Earth",
                            "Mars",
                            "Neptune",
                            "Jupiter"
                    }
            },
            {
                    {
                            "Who of these superheroes were introduced first?",
                            "Captain America",
                            "Iron Man",
                            "The Incredible Hulk",
                            "Wolverine"
                    },
                    {
                            "Which superhero was featured in 2012's movie 'The Avengers'?",
                            "Iron Man",
                            "Thor",
                            "Captain America",
                            "All of the above"
                    },
                    {
                            "When did the Black Widow make her debut?",
                            "1956",
                            "1959",
                            "1962",
                            "1964"
                    }

            },
    };
}
