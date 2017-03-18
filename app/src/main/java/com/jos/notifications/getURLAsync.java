package com.jos.notifications;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jos on 17-03-17.
 * This is our AsyncTask customized class.
 * For any network related operations, it's mandatory to instance Asynctask
 * in order to not to make a bad UX. This class ease the way of implement
 * Threads without using that class.
 *
 * Here, it will be opened an HTTP connection and it'll get an img
 */

public class getURLAsync extends AsyncTask<URL,Void,Bitmap>{

    @Override
    protected Bitmap doInBackground(URL... params) {
        HttpURLConnection huc = null;
        Bitmap bm = null;

        try {
            //get the URL object from Delay.class and open HTTP connection
            huc = (HttpURLConnection) params[0].openConnection();
            //connect to the url and get the stream, then decode to an Bitmap object
            bm = BitmapFactory.decodeStream(huc.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            //dissconnect when the connection finished.
            huc.disconnect();
        }

        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }
}

