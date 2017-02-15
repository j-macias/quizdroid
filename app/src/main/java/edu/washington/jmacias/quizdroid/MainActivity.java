package edu.washington.jmacias.quizdroid;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    public static final String MESSAGE = "edu.washington.jmacias.quizdroid.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TopicRepository topicRepository = new TopicRepository();

        final ListView listView = (ListView) findViewById(R.id.topic_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                topicRepository.getTopicListText());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MultiActivity.class);
                intent.putExtra(MESSAGE, position);
                startActivity(intent);
            }
        });
    }
}
