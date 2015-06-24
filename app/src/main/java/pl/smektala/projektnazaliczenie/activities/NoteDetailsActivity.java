package pl.smektala.projektnazaliczenie.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import pl.smektala.projektnazaliczenie.R;
import pl.smektala.projektnazaliczenie.helpers.DatabaseHelper;
import pl.smektala.projektnazaliczenie.models.TodoNoteModel;

public class NoteDetailsActivity extends Activity {

    private TextView name;
    private TextView desc;
    private TextView priority;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        initViews();
    }

    private void initViews() {
        name = (TextView) findViewById(R.id.details_name);
        desc = (TextView) findViewById(R.id.details_description);
        priority = (TextView) findViewById(R.id.details_priority);
        date = (TextView) findViewById(R.id.details_date);

        TodoNoteModel item = DatabaseHelper.getInstance(this).getItem(getIntent().getStringExtra("title"));

        if(item != null) {
            name.setText(item.getTitle());
            desc.setText(item.getDescription());
            priority.setText(Integer.toString(item.getPriority()));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            date.setText(sdf.format(item.getNotificationDate()));
        } else {
            Toast.makeText(this, "Coś poszło nie tak", Toast.LENGTH_LONG).show();
        }

        findViewById(R.id.details_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
