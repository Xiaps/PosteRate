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
import java.util.ArrayList;
import fr.eseo.mb.android.posterate.data.model.Project;
import fr.eseo.mb.android.posterate.data.model.User;
import fr.eseo.mb.android.posterate.ui.projets.ProjetsFragment;

public class AsynchTaskProjet extends AsyncTask<URL, Void, ArrayList<Project>> {

    private ProjetsFragment fragment;
    public AsynchTaskProjet(ProjetsFragment projetsFragment) {
        this.fragment = projetsFragment;
    }

    /* access modifiers changed from: protected */
    public ArrayList<Project> doInBackground(URL... urls) {
        String check = null;
        String result = null;
        ArrayList<Project> projectList = new ArrayList<Project>();
        ConnectivityManager cm = (ConnectivityManager) fragment.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            result = ComWebService.urlRequest(this.fragment.getContext(), urls[0]);

            try {
                check = new JSONObject(result).getString("result");
                Log.d("Check", check);
                JSONArray allProjects = new JSONObject(result).getJSONArray("projects");
                for(int i =0;i<allProjects.length();i++) {
                    String projectID = allProjects.getJSONObject(i).getString("projectId");
                    String title = allProjects.getJSONObject(i).getString("title");
                    String descrip = allProjects.getJSONObject(i).getString("descrip");
                    JSONObject supervisorO = allProjects.getJSONObject(i).getJSONObject("supervisor");
                    User supervisor = new User(supervisorO.getString("forename"),supervisorO.getString("surname"));
                    Boolean poster = allProjects.getJSONObject(i).getBoolean("poster");
                    int confid = allProjects.getJSONObject(i).getInt("confid");
                    JSONArray studentsAO = allProjects.getJSONObject(i).getJSONArray("students");
                    ArrayList<User> students = new ArrayList<User>();
                    for(int y = 0;y<studentsAO.length();y++) {
                        students.add(new User(studentsAO.getJSONObject(y).getString("forename"),studentsAO.getJSONObject(y).getString("surname"),studentsAO.getJSONObject(y).getString("userId")));
                    }
                    projectList.add(new Project(projectID,title,descrip,supervisor,poster,confid,students));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (projectList != null && check.equals("OK")) {
                SaveOnFile.setProjets(result,fragment.getActivity().getApplicationContext());
                return projectList;
            }
        }
        return null;
    }
    /* access modifiers changed from: protected */
    public void onPostExecute(ArrayList<Project> results) {
        if (results!=null) {
            this.fragment.setProjectList(results);
        }
    }
}