package edu.washington.jmacias.quizdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);


    }

    private String[] Math_answers = {
            "4",
            "63",
            "6x^2",
            "0"
    };
    private String[] Physics_answers = {
            "9.81 m/s^2",
            "They all fall at the same speed",
            "Mars"
    };
    private String[] Marvel_Super_Heroes_answers = {
            "Captain America",
            "All of the above",
            "1964"
    };
}
