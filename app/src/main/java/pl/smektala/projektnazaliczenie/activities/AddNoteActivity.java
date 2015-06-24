package pl.smektala.projektnazaliczenie.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import pl.smektala.projektnazaliczenie.R;
import pl.smektala.projektnazaliczenie.helpers.DatabaseHelper;
import pl.smektala.projektnazaliczenie.models.TodoNoteModel;


public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);
        initViews();
    }

    private void initViews() {
        Button addButton = (Button) findViewById(R.id.add_note_button);
        final TextView title = (TextView) findViewById(R.id.name_edit_text);
        final TextView desc = (TextView) findViewById(R.id.description_edit_text);
        final RatingBar priority = (RatingBar) findViewById(R.id.ratingBar);
        final DatePicker datePicker = (DatePicker) findViewById(R.id.date_picker);

        priority.setMax(10);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().isEmpty() || desc.getText().toString().isEmpty())
                    Toast.makeText(v.getContext(), "Dokończ uzupełnianie formularza", Toast.LENGTH_LONG).show();
                else {
                    TodoNoteModel note = new TodoNoteModel();
                    note.setTitle(title.getText().toString());
                    note.setDescription(desc.getText().toString());
                    note.setPriority(Math.round(priority.getRating()));
                    note.setNotificationDate(getDateFromDatePicker(datePicker));

                    boolean result = DatabaseHelper.getInstance(v.getContext()).addItem(note);
                    if (result) {
                        Toast.makeText(v.getContext(), "Dodano do bazy", Toast.LENGTH_LONG).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(v.getContext(), "Niepowodzenie, spróbuj ponownie", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
