package com.jos.notifications;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Delay extends AppCompatActivity {

    private ImageView iv;
    private Intent i;
    private Long alarmtime;
    //img of a boy
    private String imageurl = "https://img.memesuper.com/1089a06e3ecf468ac33ae7b7fe1a1203_oh-hell-yeah-hell-yeah-memes_427-433.jpeg";
    private URL url;
    private Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay);

        iv = (ImageView) findViewById(R.id.iv);
    }

    @Override
    protected void onStart() {
        super.onStart();

        openURLConn();
        setImg();

    }

    private void openURLConn(){
        try{
            url = new URL(imageurl);

            //get our Async object and execute the task
            getURLAsync gua = new getURLAsync();
            gua.execute(url);

            //get the result of the task
            img = gua.get();
        }
        catch (ExecutionException | InterruptedException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
    //Set the img and check if we were able to get it
    private void setImg(){
        if(img!=null) {
            iv.setImageBitmap(img);
        }
        else
            Snackbar.make(findViewById(R.id.activity_delay),
                    "Can't connect to the network right now", BaseTransientBottomBar.LENGTH_SHORT).show();
    }
}