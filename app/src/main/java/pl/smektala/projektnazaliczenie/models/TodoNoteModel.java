package pl.smektala.projekt.models;

import java.util.Date;

import io.realm.RealmObject;

public class TodoNoteModel extends RealmObject {

    private String title;
    private String description;
    private Date notificationDate;
    private int priority;

    public TodoNoteModel(String title, String description, Date notificationDate, int priority) {
        this.title = title;
        this.description = description;
        this.notificationDate = notificationDate;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public int getPriority() {
        return priority;
    }
}
