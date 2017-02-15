package edu.washington.jmacias.quizdroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AnswerFrag extends Fragment {

    private static final String TAG = "AnswerFrag";
    private static final String ARG_ANSWERED_TEXT = "answeredText";
    private static final String ARG_TOPIC_INDEX = "topicIndex";
    private static final String ARG_QUESTION_NUMBER = "questionNumber";
    private static final String ARG_QUESTIONS_CORRECT = "questionsCorrect";

    private int answeredIndex;
    private int topicIndex;
    private int questionNumber;
    private int questionsCorrect;

    private OnFragmentInteractionListener mListener;

    public AnswerFrag() {
        // Required empty public constructor
    }

    public static AnswerFrag newInstance(int answeredIndex, int topicIndex, int questionNumber,
                                         int questionsCorrect) {
        AnswerFrag fragment = new AnswerFrag();
        Bundle args = new Bundle();
        args.putInt(ARG_ANSWERED_TEXT, answeredIndex);
        args.putInt(ARG_TOPIC_INDEX, topicIndex);
        args.putInt(ARG_QUESTION_NUMBER, questionNumber);
        args.putInt(ARG_QUESTIONS_CORRECT, questionsCorrect);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            answeredIndex = getArguments().getInt(ARG_ANSWERED_TEXT);
            topicIndex = getArguments().getInt(ARG_TOPIC_INDEX);
            questionNumber = getArguments().getInt(ARG_QUESTION_NUMBER);
            questionsCorrect = getArguments().getInt(ARG_QUESTIONS_CORRECT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View answerView = inflater.inflate(R.layout.fragment_answer, container, false);
        TopicRepository topicRepository = new TopicRepository();
        final Topic topic = topicRepository.getTopicList().get(topicIndex);
        final Question question = topic.getQuestionList().get(questionNumber - 1);
        final int correctIndex = question.getCorrectIndex();

        final int updatedQuestionsCorrect = questionsCorrect +
                (answeredIndex == correctIndex ? 1 : 0);
        TextView answeredTextView = (TextView) answerView.findViewById(R.id.answeredText);
        answeredTextView.setText(question.getAnswers()[answeredIndex]);
        TextView correctText = (TextView) answerView.findViewById(R.id.correctText);
        correctText.setText(question.getAnswers()[correctIndex]);
        TextView numberCorrectText = (TextView) answerView.findViewById(R.id.numberCorrectText);
        numberCorrectText.setText("You have " + updatedQuestionsCorrect + " out of "
                + questionNumber + " correct");
        final Button nextButton = (Button) answerView.findViewById(R.id.nextButton);
        if (questionNumber >= topic.getQuestionCount()) {
            nextButton.setText("Finish");
        }
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionNumber < topic.getQuestionCount()) {
                    mListener.onAnswerFragInteraction(updatedQuestionsCorrect);
                } else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        return answerView;
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
        void onAnswerFragInteraction(int questionsCorrect);
    }

    /*
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
    };*/
}
