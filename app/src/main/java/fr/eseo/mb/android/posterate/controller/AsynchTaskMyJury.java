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
import fr.eseo.mb.android.posterate.data.model.Jury;
import fr.eseo.mb.android.posterate.data.model.LoggedInUser;
import fr.eseo.mb.android.posterate.data.model.Project;
import fr.eseo.mb.android.posterate.data.model.User;
import fr.eseo.mb.android.posterate.ui.jury.JuryFragment;
import fr.eseo.mb.android.posterate.ui.login.LoginActivity;
public class AsynchTaskMyJury extends AsyncTask<URL, Void, ArrayList<Jury>> {

    private JuryFragment fragment;
    public AsynchTaskMyJury(JuryFragment juryFragment) {
        this.fragment= juryFragment;
    }
    /* access modifiers changed from: protected */
    public ArrayList<Jury> doInBackground(URL... urls) {
        String check = null;
        String result = null;
        ArrayList<Project> projectList = new ArrayList<Project>();
        ArrayList<Jury> juryArrayList = new ArrayList<Jury>();
        ConnectivityManager cm = (ConnectivityManager) fragment.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            result = ComWebService.urlRequest(this.fragment.getContext(), urls[0]);
            try {
                Log.d("result", result);
                check = new JSONObject(result).getString("result");

                Log.d("Check", check);
                JSONArray allJury = new JSONObject(result).getJSONArray("juries");
                for (int j = 0; j < allJury.length(); j++) {

                    String idJury = allJury.getJSONObject(j).getString("idJury");
                    String date = allJury.getJSONObject(j).getString("date");
                    JSONObject infos = allJury.getJSONObject(j).getJSONObject("info");
                    ArrayList<User> members = new ArrayList<>();
                    JSONArray allMembers = infos.getJSONArray("members");
                    for (int i = 0; i < allMembers.length(); i++) {
                        members.add(new User(allMembers.getJSONObject(i).getString("forename"), allMembers.getJSONObject(i).getString("surname")));
                    }
                    JSONArray allProjects = infos.getJSONArray("projects");
                    for (int i = 0; i < allProjects.length(); i++) {
                        String projectID = allProjects.getJSONObject(i).getString("projectId");
                        String title = allProjects.getJSONObject(i).getString("title");
                        int confid = allProjects.getJSONObject(i).getInt("confid");
                        JSONObject supervisorJson = allProjects.getJSONObject(i).getJSONObject("supervisor");
                        User supervisor = new User(supervisorJson.getString("forename"),supervisorJson.getString("surname"),"supervisor");
                        Boolean poster = allProjects.getJSONObject(i).getBoolean("poster");
                        projectList.add(new Project(projectID, title, confid, supervisor ));
                    }
                    juryArrayList.add(new Jury(idJury, date, members, projectList));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (juryArrayList != null && check.equals("OK")) {
                return juryArrayList;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(ArrayList<Jury> result) {
        if (result!=null) {
            this.fragment.setJuryList(result);
        }
    }
    //"https://172.24.5.16/pfe/webservice.php?q=MYJUR&user="+username+"&token="+<token data>
}