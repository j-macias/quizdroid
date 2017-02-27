package edu.washington.jmacias.quizdroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MultiActivity extends AppCompatActivity implements
        TopicOverviewFrag.OnFragmentInteractionListener,
        QuestionFrag.OnFragmentInteractionListener,
        AnswerFrag.OnFragmentInteractionListener {

    private static final String TAG = "MultiActivity";
    private int topicIndex;
    private int questionNumber;
    private int questionsCorrect;
    public static TopicRepository topicRepository;

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().beginTransaction().remove(getFragmentManager()
                    .findFragmentById(R.id.fragment_container)).commit();
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        topicIndex = getIntent().getExtras().getInt(MainActivity.MESSAGE);
        questionNumber = 1;
        questionsCorrect = 0;

        TopicOverviewFrag topicOverviewFrag = TopicOverviewFrag.newInstance(topicIndex);
        transactFragment(topicOverviewFrag, false);
    }

    @Override
    public void onOverviewFragInteraction() {
        QuestionFrag questionFrag = QuestionFrag.newInstance(topicIndex, questionNumber);
        transactFragment(questionFrag, true);
    }

    @Override
    public void onQuestionFragInteraction(int answeredIndex) {
        AnswerFrag answerFrag = AnswerFrag.newInstance(answeredIndex, topicIndex, questionNumber,
                questionsCorrect);
        transactFragment(answerFrag, false);
    }

    @Override
    public void onAnswerFragInteraction(int updatedQuestionsCorrect) {
        questionNumber++;
        questionsCorrect = updatedQuestionsCorrect;
        onOverviewFragInteraction();
    }

    private void transactFragment(Fragment fragment, boolean addToStack) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (!addToStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
