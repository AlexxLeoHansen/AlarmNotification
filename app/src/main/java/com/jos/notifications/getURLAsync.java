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
 */

public class getURLAsync extends AsyncTask<URL,Void,Bitmap>{

    @Override
    protected Bitmap doInBackground(URL... params) {
        HttpURLConnection huc = null;
        Bitmap bm = null;

        try {
            huc = (HttpURLConnection) params[0].openConnection();
            bm = BitmapFactory.decodeStream(huc.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            huc.disconnect();
        }

        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }
}

