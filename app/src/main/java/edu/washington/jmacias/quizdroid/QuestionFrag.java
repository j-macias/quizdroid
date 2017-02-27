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
        QuizApp app = (QuizApp) getActivity().getApplicationContext();
        final View questionView = inflater.inflate(R.layout.fragment_question, container, false);
        final Question question = app.getRepository().getTopicList().get(topicIndex)
                .getQuestionList().get(questionNumber - 1);

        TextView headerText = (TextView) questionView.findViewById(R.id.questionHeader);
        headerText.setText(app.getRepository().getTopicTitles()[topicIndex] + " Question "
                + questionNumber + ":");
        TextView questionText = (TextView) questionView.findViewById(R.id.questionText);
        questionText.setText(question.getQuestionText());
        final RadioGroup optionRadioGroup = (RadioGroup)
                questionView.findViewById(R.id.answerOptions);
        for (int i = 0; i < optionRadioGroup.getChildCount(); i++) {
            ((RadioButton) optionRadioGroup.getChildAt(i)).setText(question.getAnswers()[i]);
        }

        final Button submitButton = (Button) questionView.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answerID = optionRadioGroup.indexOfChild(questionView.findViewById(
                        optionRadioGroup.getCheckedRadioButtonId()));
                if (answerID > -1) {
                    mListener.onQuestionFragInteraction(answerID);
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
        void onQuestionFragInteraction(int answeredIndex);
    }
}
