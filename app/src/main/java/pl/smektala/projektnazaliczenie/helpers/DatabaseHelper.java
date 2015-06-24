package pl.smektala.projektnazaliczenie.helpers;

import android.content.Context;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import pl.smektala.projektnazaliczenie.models.TodoNoteModel;

public class DatabaseHelper {

    private static DatabaseHelper instance;
    private static Realm realmInstance;

    public DatabaseHelper(Context cntx) {
        realmInstance = Realm.getInstance(cntx);
    }

    public static synchronized DatabaseHelper getInstance(Context cntx) {
        if (instance == null || realmInstance == null) instance = new DatabaseHelper(cntx);

        return instance;
    }

    public ArrayList<TodoNoteModel> getList() {
        return new ArrayList<>(realmInstance.allObjectsSorted(TodoNoteModel.class, "priority", false));
    }

    public TodoNoteModel getItem(String title) {
        RealmQuery<TodoNoteModel> query = realmInstance.where(TodoNoteModel.class);

        query.equalTo("title", title);

        return query.findFirst();
    }

    public boolean addItem(TodoNoteModel note) {
        if (note == null) return false;

        realmInstance.beginTransaction();
        realmInstance.copyToRealm(note);
        realmInstance.commitTransaction();

        return true;
    }

    public boolean deleteItem(TodoNoteModel note) {
        RealmQuery<TodoNoteModel> query = realmInstance.where(TodoNoteModel.class);
        query.equalTo("title", note.getTitle());

        TodoNoteModel found = query.findFirst();

        if (found == null) return false;

        realmInstance.beginTransaction();
        found.removeFromRealm();
        realmInstance.commitTransaction();

        return true;
    }
}
