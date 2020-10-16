package fr.eseo.mb.android.posterate.controller;
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
public class AsynchTaskRole extends AsyncTask<URL, Void, String> {

    private LoginActivity activity;
    public AsynchTaskRole(LoginActivity loginActivity) {
        this.activity = loginActivity;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(URL... urls) {
        String results = null;
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            results = ComWebService.urlRequest(this.activity.getApplicationContext(), urls[0]);
            Log.d("ASYNC", "Results=" + results);

            try {
                String check = new JSONObject(results).getString("result");
                Log.d("Check", check);
                JSONArray infos = new JSONObject(results).getJSONArray("info");
                JSONObject info = infos.getJSONObject(0);
                String nom = info.getString("surname");
                String prenom = info.getString("forename");
                Log.d("Full name", nom+" "+prenom);
                int idRole = info.getInt("idRole");
                Log.d(" idRole",  ""+idRole);
                if ( idRole != 0 && check.equals("OK")) {
                    this.activity.okLog=true;
                    LoggedInUser.fullName = nom+" "+prenom;
                    if(idRole==4) {
                        LoggedInUser.role="Communication Service Member";
                    } else {
                        LoggedInUser.role="Jury Member";
                    }
                    return  ""+idRole;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /* access modifiers changed from: protected */
    public void onPostExecute(String results) {
        this.activity.goLog();
    }
}