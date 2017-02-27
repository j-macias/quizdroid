package edu.washington.jmacias.quizdroid;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class TopicOverviewFrag extends Fragment {

    private static final String TAG = "TopicOverviewFrag";
    private static final String ARG_TOPIC_INDEX = "topicIndex";

    private int topicIndex;

    private OnFragmentInteractionListener mListener;

    public TopicOverviewFrag() {
        // Required empty public constructor
    }

    public static TopicOverviewFrag newInstance(int topicIndex) {
        TopicOverviewFrag fragment = new TopicOverviewFrag();
        Bundle args = new Bundle();
        args.putInt(ARG_TOPIC_INDEX, topicIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            topicIndex = getArguments().getInt(ARG_TOPIC_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        QuizApp app = (QuizApp) getActivity().getApplicationContext();
        View topicOverviewView = inflater.inflate(R.layout.fragment_topic_overview,
                container, false);

        TextView title = (TextView) topicOverviewView.findViewById(R.id.title);
        title.setText(app.getRepository().getTopicTitles()[topicIndex]);
        TextView description = (TextView) topicOverviewView.findViewById(R.id.description);
        description.setText(app.getRepository().getTopicLongDescriptions()[topicIndex]);

        final Button beginButton = (Button) topicOverviewView.findViewById(R.id.beginButton);
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onOverviewFragInteraction();
            }
        });
        return topicOverviewView;
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
        void onOverviewFragInteraction();
    }
}
