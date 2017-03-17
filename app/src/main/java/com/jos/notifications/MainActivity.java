package com.jos.notifications;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private TimePicker tp;
    private long alarm;
    private Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.b_getnotif);
        tp = (TimePicker) findViewById(R.id.timePicker);
    }

    @Override
    protected void onStart() {
        super.onStart();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCalendar();
                broadcastAlarm();
                Toast.makeText(getApplicationContext(),"Alarm set: "+tp.getCurrentHour()+":"+tp.getCurrentMinute(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCalendar(){
        //Get Calendar instance at the current time
        c = Calendar.getInstance();
        //Set and get the hour 0-24 and minute format from TimePicker
        //NOTE: Time is not calculated until we call getTimeInMillis or getTime functions
        c.set(Calendar.HOUR_OF_DAY,tp.getCurrentHour());
        c.set(Calendar.MINUTE,tp.getCurrentMinute());
        c.set(Calendar.SECOND,0);

        //Calculate current time with our setter
        alarm = c.getTimeInMillis();
    }
    private void broadcastAlarm(){

        //Call ClockReceiver, where BroadcastReceiver is
        Intent i = new Intent(MainActivity.this,ClockReceiver.class);
        i.putExtra("time",alarm);

        //This is a special intent which we don't know WHEN is gonna happend.
        //It will happend when our alarm occurs. With this kind of implementation (PendingIntent + Broadcast)
        //Our app doesn't need to be in the foreground to Broadcast Receiver appears.
        //@Params: Caller activity, RequestCode, Intent, Pending intent, Flag: In this case, update the
        //intents data each time the PendingIntent occurs.
        PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        //Set up alarm once in UTC mode
        am.set(AlarmManager.RTC_WAKEUP,alarm,pi);
        //Set up alarm each 1 minute
        //am.setRepeating(AlarmManager.RTC_WAKEUP,alarm,1000*60,pi);
    }
}
