package pl.smektala.projektnazaliczenie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import pl.smektala.projektnazaliczenie.R;
import pl.smektala.projektnazaliczenie.adapters.TodoNotesAdapter;
import pl.smektala.projektnazaliczenie.helpers.DatabaseHelper;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    private ListView list;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            initList();
        }
    }

    private void initViews() {
        list = (ListView) findViewById(R.id.todo_list);
        addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), AddNoteActivity.class), REQUEST_CODE);
            }
        });
    }

    private void initList() {
        list.setAdapter(new TodoNotesAdapter(DatabaseHelper.getInstance(this).getList()));
    }
}
