package fr.eseo.mb.android.posterate.controller;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import fr.eseo.mb.android.posterate.data.model.LoggedInUser;
import fr.eseo.mb.android.posterate.ui.login.LoginActivity;
import fr.eseo.mb.android.posterate.ui.projets.ProjectDetailActivity;

public class AsynchTaskGrade extends AsyncTask<URL, Void, String> {


    private ProjectDetailActivity activity;

    public AsynchTaskGrade(ProjectDetailActivity activity) {
        this.activity = activity;
    }
    /* access modifiers changed from: protected */
    public String doInBackground(URL... urls) {
        String results = null;
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {

            results = ComWebService.urlRequest(this.activity.getApplicationContext(), urls[0]);
            try {
                String check = new JSONObject(results).getString("result");
                Log.d("Check", check);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String results) {
    }

    //"https://172.24.5.16/pfe/webservice.php?q=NEWNT&user="+username+">&proj="+<project id>+"&student="+<student id>"+&note="+<note>+"token="+<token data>

}