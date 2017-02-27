package edu.washington.jmacias.quizdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        final QuizApp app = (QuizApp) this.getApplication();
        final EditText urlField = (EditText) findViewById(R.id.editTextURL);
        urlField.setText(app.getURL());
        final EditText intervalField = (EditText) findViewById(R.id.editTextMinutes);
        intervalField.setText(String.valueOf(app.getDownloadInterval()));

        final Button saveButton = (Button) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.setURL(urlField.getText().toString());
                app.setDownloadInterval(Integer.parseInt(intervalField.getText().toString()));
                onBackPressed();
            }
        });
    }
}
