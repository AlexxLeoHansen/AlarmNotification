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
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
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
        // get the intent alarm time
        long hour = intent.getLongExtra("time",0);

        //give date format in HH-MM-SS
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        //convert alarm time from ms to HH-MM-SS
        String dateString = formatter.format(hour);

        //Instantiate intent for the notification's action
        Intent iB = new Intent(context,Delay.class);
        //this is mandatory, because again, we dont know when we are gonna tap the notification
        //now we will call to an activity
        PendingIntent resultPi = PendingIntent.getActivity(context, 0,iB,PendingIntent.FLAG_UPDATE_CURRENT);

        //Notification builder, with all the params of the notif
        notif = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setContentTitle("Alarm Hour")
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .setContentText("Alarming at "+dateString+" ")
                .setColor(Color.BLUE)
                .setShowWhen(true)
                .setVibrate(new long[] {100,100,100,500})
                .setContentIntent(resultPi);

        //get notification manager service
        nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        //build the notification with id for later modifications
        nm.notify(mId,notif.build());
    }
}