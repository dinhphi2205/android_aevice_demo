package com.demo.aevicedemo.utils;


import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.core.app.NotificationManagerCompat;

import com.demo.aevicedemo.R;
import com.demo.aevicedemo.views.MainActivity;

public class AlarmService extends IntentService {
    private static final int NOTIFICATION_ID = 3991;

    public AlarmService() {
        super("DemoAlarmService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Reminder to take medicine");
        builder.setContentText("Try to take medicine on time");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
    }
}