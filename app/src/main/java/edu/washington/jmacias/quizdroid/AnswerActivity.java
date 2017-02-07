package edu.washington.jmacias.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    public static final String TAG = "AnswerActivity";
    public static final String MESSAGE = "edu.washington.jmacias.quizdroid.AnswerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        final int topicIndex = getIntent().getExtras().getInt("topicIndex");
        final int questionNumber = getIntent().getExtras().getInt("questionNumber");
        final int questionsCorrect = getIntent().getExtras().getInt("questionsCorrect");
        final String userAnswer = getIntent().getExtras().getString("userAnswer");
        int correctIncrement = userAnswer.equals(answers[topicIndex][questionNumber - 1]) ? 1 : 0;
        final int updatedQuestionsCorrect = questionsCorrect + correctIncrement;

        TextView answeredText = (TextView) findViewById(R.id.answeredText);
        answeredText.setText(userAnswer);
        TextView correctText = (TextView) findViewById(R.id.correctText);
        correctText.setText(answers[topicIndex][questionNumber - 1]);
        TextView numberCorrectText = (TextView) findViewById(R.id.numberCorrectText);
        numberCorrectText.setText("You have " + updatedQuestionsCorrect + " out of "
                + questionNumber + " correct");
        final Button nextButton = (Button) findViewById(R.id.nextButton);
        if (questionNumber >= answers[topicIndex].length) {
            nextButton.setText("Finish");
        }
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (questionNumber < answers[topicIndex].length) {
                    intent = new Intent(AnswerActivity.this, QuestionActivity.class);
                    intent.putExtra("topicIndex", topicIndex);
                    intent.putExtra("questionNumber", questionNumber + 1);
                    intent.putExtra("questionsCorrect", updatedQuestionsCorrect);
                } else {
                    intent = new Intent(AnswerActivity.this, MainActivity.class);
                }
                startActivity(intent);
            }
        });
    }

    private String[][] answers = {
            {                           //Math
                    "4",
                    "63",
                    "6x^2",
                    "0"
            },
            {                           //Physics
                    "9.81 m/s^2",
                    "They all fall at the same speed",
                    "Mars"
            },
            {                           //Marvel Super Heroes
                    "Captain America",
                    "All of the above",
                    "1964"
            }
    };
}
