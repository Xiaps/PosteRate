package fr.eseo.mb.android.posterate.controller;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Base64;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import fr.eseo.mb.android.posterate.ui.projets.FullPosterActivity;
import fr.eseo.mb.android.posterate.ui.projets.ProjectDetailActivity;

public class AsynchTaskPosterFullPic extends AsyncTask<URL, Void, Bitmap> {

    private FullPosterActivity activity;
    public AsynchTaskPosterFullPic(FullPosterActivity activity) {
        this.activity = activity;
    }
    /* access modifiers changed from: protected */
    public Bitmap doInBackground(URL... urls) {
        String result = null;
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            result = ComWebService.urlRequest(this.activity.getApplicationContext(), urls[0]);
            //System.out.println(result);
            if (result!=null) {
                URL posterUrl = null;
                try {
                    posterUrl = new URL(result);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                byte[] monStream = Base64.decode(result,Base64.DEFAULT);
                return BitmapFactory.decodeByteArray(monStream,0, monStream.length);

            }
        }
        return null;
    }



    /* access modifiers changed from: protected */
    public void onPostExecute(Bitmap results) {
        this.activity.updatePoster(results);
    }
    //"https://172.24.5.16/pfe/webservice.php?q=POSTR&user="+username+">&proj="+<project id>+"&style="+<style>+"token="+<token data>
}