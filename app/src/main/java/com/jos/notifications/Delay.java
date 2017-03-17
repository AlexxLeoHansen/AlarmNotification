package com.jos.notifications;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Delay extends AppCompatActivity {

    private ImageView iv;
    private Intent i;
    private Long alarmtime;
    private String imageurl = "https://img.memesuper.com/1089a06e3ecf468ac33ae7b7fe1a1203_oh-hell-yeah-hell-yeah-memes_427-433.jpeg";
    private URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay);

        iv = (ImageView) findViewById(R.id.iv);
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            url = new URL(imageurl);

            getURLAsync gua = new getURLAsync();
            gua.execute(url);

            Bitmap img = gua.get();
            if(img ==null) {
                img = BitmapFactory.decodeResource(this.getResources(), android.R.drawable.alert_dark_frame);
                iv.setImageBitmap(img);
            }
            else
                iv.setImageBitmap(img);

        } catch (ExecutionException | InterruptedException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}