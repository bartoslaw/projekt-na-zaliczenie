package pl.smektala.projektnazaliczenie.models;

import java.util.Date;

import io.realm.RealmObject;

public class TodoNoteModel extends RealmObject {

    private String title;
    private String description;
    private Date notificationDate;
    private int priority;

    public TodoNoteModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
