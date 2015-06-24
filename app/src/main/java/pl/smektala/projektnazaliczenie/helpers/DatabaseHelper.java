package pl.smektala.projekt.helpers;

import android.content.Context;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import pl.smektala.projekt.models.TodoNoteModel;

public class DatabaseHelper {

    private static DatabaseHelper instance;
    private static Realm realmInstance;

    public DatabaseHelper(Context cntx) {
        realmInstance = Realm.getInstance(cntx);
    }

    public static synchronized DatabaseHelper getInstance(Context cntx) {
        if(instance == null || realmInstance == null)
            instance = new DatabaseHelper(cntx);

        return instance;
    }

    public ArrayList<TodoNoteModel> getList() {
        RealmQuery<TodoNoteModel> query = realmInstance.where(TodoNoteModel.class);

        return new ArrayList<>(query.findAll());
    }

    public boolean addItem(TodoNoteModel note) {
        if(note == null)
            return false;

        realmInstance.beginTransaction();
        realmInstance.copyToRealm(note);
        realmInstance.commitTransaction();

        return true;
    }
}
