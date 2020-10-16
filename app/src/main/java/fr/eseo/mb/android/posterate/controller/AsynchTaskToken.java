package fr.eseo.mb.android.posterate.controller;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import fr.eseo.mb.android.posterate.data.model.LoggedInUser;
import fr.eseo.mb.android.posterate.ui.login.LoginActivity;
public class AsynchTaskToken extends AsyncTask<URL, Void, String> {

    private LoginActivity activity;
    public AsynchTaskToken(LoginActivity loginActivity) {
        this.activity = loginActivity;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(URL... urls) {
        String results = null;
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            results = ComWebService.urlRequest(this.activity.getApplicationContext(), urls[0]);
            //Log.d("ASYNC", "Results=" + results);

            try {
                String check = new JSONObject(results).getString("result");
                Log.d("Check", check);
                String token = new JSONObject(results).getString("token");
                Log.d("Token", token);
                if (token != null && check.equals("OK")) {
                    this.activity.okLog=true;
                    LoggedInUser.token=token;
                    return token;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /* access modifiers changed from: protected */
    public void onPostExecute(String results) {
        try {
            this.activity.getRole();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}