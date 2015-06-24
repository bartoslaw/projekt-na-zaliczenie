package pl.smektala.projektnazaliczenie.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import pl.smektala.projektnazaliczenie.R;
import pl.smektala.projektnazaliczenie.activities.NoteDetailsActivity;
import pl.smektala.projektnazaliczenie.helpers.DatabaseHelper;
import pl.smektala.projektnazaliczenie.models.TodoNoteModel;


public class TodoNotesAdapter extends BaseAdapter {

    private ArrayList<TodoNoteModel> items;

    public TodoNotesAdapter(ArrayList<TodoNoteModel> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public TodoNoteModel getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).hashCode();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        NoteViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_todo, parent, false);
            vh = new NoteViewHolder();

            vh.name = (TextView) convertView.findViewById(R.id.item_name);
            vh.priority = (TextView) convertView.findViewById(R.id.item_priority);
            vh.checkBox = (CheckBox) convertView.findViewById(R.id.item_delete);

            convertView.setTag(vh);
        } else {
            vh = (NoteViewHolder) convertView.getTag();
        }

        final TodoNoteModel item = items.get(position);
        vh.name.setText(item.getTitle());
        vh.priority.setText(Integer.toString(item.getPriority()));
        vh.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DatabaseHelper.getInstance(buttonView.getContext()).deleteItem(item);
                items.remove(position);
                items.trimToSize();
                notifyDataSetChanged();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NoteDetailsActivity.class);
                i.putExtra("title", item.getTitle());
                v.getContext().startActivity(i);
            }
        });

        return convertView;
    }

    private class NoteViewHolder {
        public TextView name;
        public TextView priority;
        public CheckBox checkBox;
    }
}
