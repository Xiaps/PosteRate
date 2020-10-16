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
import java.util.ArrayList;

import fr.eseo.mb.android.posterate.data.model.LoggedInUser;
import fr.eseo.mb.android.posterate.data.model.Project;
import fr.eseo.mb.android.posterate.data.model.User;
import fr.eseo.mb.android.posterate.ui.login.LoginActivity;

public class AsynchTaskRandomPoster extends AsyncTask<URL, Void, ArrayList<Project>> {


    private Activity activity;

    public AsynchTaskRandomPoster(Activity activity) {
        this.activity = activity;
    }
    /* access modifiers changed from: protected */
    public ArrayList<Project> doInBackground(URL... urls) {
        String check = null;
        String result = null;
        ArrayList<Project> projectList = new ArrayList<Project>();

        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {

            result = ComWebService.urlRequest(this.activity.getApplicationContext(), urls[0]);

            try {

                check = new JSONObject(result).getString("result");
                Log.d("Check", check);

                JSONArray allProjects = new JSONObject(result).getJSONArray("projects");
                for (int i = 0; i < allProjects.length(); i++) {

                    String projectID = allProjects.getJSONObject(i).getString("projectId");
                    String title = allProjects.getJSONObject(i).getString("title");
                    String descrip = allProjects.getJSONObject(i).getString("descrip");
                    Boolean poster = allProjects.getJSONObject(i).getBoolean("poster");

                    projectList.add(new Project(projectID, title, descrip, poster));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (projectList != null && check.equals("OK")) {
                return projectList;
            }
        }
        return null;

    }


    /* access modifiers changed from: protected */
    public void onPostExecute(String result) {
    }

    //"https://172.24.5.16/pfe/webservice.php?q=PORTE&user="+username+"&seed="+<seed>+"token="+<token data>

}