package edu.washington.jmacias.quizdroid;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class QuestionFrag extends Fragment {

    private static final String TAG = "QuestionFrag";
    private static final String ARG_TOPIC_INDEX = "topicIndex";
    private static final String ARG_QUESTION_NUMBER = "questionNumber";

    private int topicIndex;
    private int questionNumber;

    private OnFragmentInteractionListener mListener;

    public QuestionFrag() {
        // Required empty public constructor
    }

    public static QuestionFrag newInstance(int topicIndex, int questionNumber) {
        QuestionFrag fragment = new QuestionFrag();
        Bundle args = new Bundle();
        args.putInt(ARG_TOPIC_INDEX, topicIndex);
        args.putInt(ARG_QUESTION_NUMBER, questionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            topicIndex = getArguments().getInt(ARG_TOPIC_INDEX);
            questionNumber = getArguments().getInt(ARG_QUESTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View questionView = inflater.inflate(R.layout.fragment_question, container, false);

        final String[] topics = getResources().getStringArray(R.array.topics);
        TextView headerText = (TextView) questionView.findViewById(R.id.questionHeader);
        headerText.setText(topics[topicIndex] + " Question " + questionNumber + ":");
        TextView questionText = (TextView) questionView.findViewById(R.id.questionText);
        questionText.setText(questionsOptions[topicIndex][questionNumber - 1][0]);
        final RadioGroup optionRadioGroup = (RadioGroup) questionView.findViewById(R.id.answerOptions);
        for (int i = 0; i < optionRadioGroup.getChildCount(); i++) {
            ((RadioButton) optionRadioGroup.getChildAt(i)).setText(questionsOptions[topicIndex]
                    [questionNumber - 1][i + 1]);
        }

        final Button submitButton = (Button) questionView.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answerID = optionRadioGroup.indexOfChild(questionView.findViewById(
                        optionRadioGroup.getCheckedRadioButtonId()));
                if (answerID > -1) {
                    mListener.onQuestionFragInteraction(questionsOptions[topicIndex]
                            [questionNumber - 1][answerID + 1]);
                }
            }
        });
        return questionView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onQuestionFragInteraction(String answeredText);
    }

    private String[][][] questionsOptions = {
            {                                               //Math
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
            {                                               //Physics
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
            {                                               //Marvel Super Heroes
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
