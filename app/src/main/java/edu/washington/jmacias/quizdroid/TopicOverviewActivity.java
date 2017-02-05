package edu.washington.jmacias.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class TopicOverviewActivity extends AppCompatActivity {

    public static final String TAG = "TopicOverviewActivity";
    public static final String MESSAGE = "edu.washington.jmacias.quizdroid.TopicOverviewActivity";
    private Map<String, String> topicText = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);
        final String[] topics = getResources().getStringArray(R.array.topics);
        topicText.put(topics[0], "Questions on math (4 questions)");
        topicText.put(topics[1], "Questions on physics (3 questions)");
        topicText.put(topics[2], "Questions on Marvel super heroes (3 questions)");
        final int topicIndex = getIntent().getExtras().getInt(MainActivity.MESSAGE);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(topics[topicIndex]);
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(topicText.get(topics[topicIndex]));

        final Button beginButton = (Button) findViewById(R.id.beginButton);
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopicOverviewActivity.this, QuestionActivity.class);
                intent.putExtra(MESSAGE, topicIndex);
                startActivity(intent);
            }
        });
    }
}
