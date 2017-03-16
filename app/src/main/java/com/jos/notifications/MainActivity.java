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

                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

                c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY,tp.getCurrentHour());
                c.set(Calendar.MINUTE,tp.getCurrentMinute());
                c.set(Calendar.SECOND,0);

                long alarmtime = c.getTimeInMillis();
                Intent i = new Intent(MainActivity.this,ClockReceiver.class);
                i.putExtra("time",alarmtime);
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
                am.set(AlarmManager.RTC_WAKEUP,alarmtime,pi);
                //am.setRepeating(AlarmManager.RTC_WAKEUP,alarmtime,1000*60,pi);

                Toast.makeText(getApplicationContext(),"Time set: "+tp.getCurrentHour()+":"+tp.getCurrentMinute(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
