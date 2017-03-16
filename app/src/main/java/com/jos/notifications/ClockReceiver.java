package com.jos.notifications;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.NOTIFICATION_SERVICE;

public class ClockReceiver extends BroadcastReceiver {
    private static int mId = 1000;
    private NotificationCompat.Builder notif;
    private NotificationManager nm;
    private Calendar c;

    public ClockReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        long hour = intent.getExtras().getLong("time");

        notif = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setContentTitle("Alarm Hour")
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .setContentText("Alarming at "+hour)
                .setColor(Color.BLUE)
                .setShowWhen(true); //show timestamp

        nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        nm.notify(mId,notif.build());
    }
}
