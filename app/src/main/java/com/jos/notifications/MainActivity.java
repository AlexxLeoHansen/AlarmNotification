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
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

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

        c = Calendar.getInstance();

        Toast.makeText(this,String.valueOf(c.get(Calendar.HOUR_OF_DAY))+String.valueOf(c.get(Calendar.MINUTE)),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent i = new Intent(MainActivity.this,ClockReceiver.class);
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this,0,i,0);

                c.setTimeInMillis(System.currentTimeMillis());
                c.set(Calendar.HOUR_OF_DAY,tp.getCurrentHour());
                c.set(Calendar.MINUTE,tp.getCurrentMinute());

                am.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
                //am.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),1000*60,pi);
                i.putExtra("time",c.getTimeInMillis());
                Toast.makeText(v.getContext(),"Time set: "+tp.getCurrentHour()+":"+tp.getCurrentMinute(),Toast.LENGTH_SHORT).show();

            }
        });



    }
}
