package pl.smektala.projektnazaliczenie.helpers;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Date;
import java.util.Random;

import pl.smektala.projektnazaliczenie.R;
import pl.smektala.projektnazaliczenie.receivers.NotificationPublisher;

public class NotificationsHelper {

    public static void scheduleNotification(Context cntx, Date time, String title, String description) {
        Notification.Builder builder = new Notification.Builder(cntx);
        builder.setContentTitle(title);
        builder.setContentText(description);
        builder.setSmallIcon(R.mipmap.ic_launcher);

        Intent notificationIntent = new Intent(cntx, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, new Random().nextInt());
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, builder.build());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(cntx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) cntx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, time.getTime(), pendingIntent);
    }
}
