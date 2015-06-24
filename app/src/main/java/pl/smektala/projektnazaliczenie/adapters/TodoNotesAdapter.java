package pl.smektala.projekt.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import pl.smektala.projekt.R;
import pl.smektala.projekt.models.TodoNoteModel;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        NoteViewHolder vh;
        if(convertView == null) {
            convertView = LayoutInflater.from(convertView.getContext()).inflate(R.layout.activity_main, parent, false);
            vh = new NoteViewHolder();

            convertView.setTag(vh);
        } else {
            vh = (NoteViewHolder) convertView.getTag();
        }

        //TODO fill out adapter item

        return convertView;
    }

    private class NoteViewHolder {
        //TODO finish view holder
    }
}
