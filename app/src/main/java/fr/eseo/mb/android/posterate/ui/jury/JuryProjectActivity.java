package fr.eseo.mb.android.posterate.ui.jury;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import fr.eseo.mb.android.posterate.R;
import fr.eseo.mb.android.posterate.controller.AsynchTaskProjet;
import fr.eseo.mb.android.posterate.data.model.Jury;
import fr.eseo.mb.android.posterate.data.model.LoggedInUser;
import fr.eseo.mb.android.posterate.data.model.Project;
import fr.eseo.mb.android.posterate.viewAdapter.JuryProjectsListViewAdapter;
import fr.eseo.mb.android.posterate.viewAdapter.JurySummaryListViewAdapter;
import fr.eseo.mb.android.posterate.viewAdapter.ProjectSummaryListViewAdapter;

public class JuryProjectActivity extends AppCompatActivity {

    private JuryProjectsListViewAdapter viewAdapter;
    private ArrayList<Project> projectList = ProjectSummaryListViewAdapter.projectList;
    private ArrayList<Project> juryProjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury_project);
        ArrayList<Jury> juryList = JurySummaryListViewAdapter.juryList;
        TextView txtTest = (TextView) findViewById(R.id.testJuryProjets);
        juryProjectList = new ArrayList<>();




        RecyclerView recycler = findViewById(R.id.list_jury_projects);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        recycler.setHasFixedSize(true);
        viewAdapter = new JuryProjectsListViewAdapter(this);
        recycler.setAdapter(viewAdapter);


        int nbProjects = getIntent().getIntExtra("NBPROJECT",0);
        int id = getIntent().getIntExtra("IDPROJECT0",0);

        String ids="";
        for(int i=0;i<nbProjects;i++){
            //ids+=getIntent().getIntExtra("IDPROJECT"+i,0)+" ";
            juryProjectList.add(projectList.get(getIntent().getIntExtra("IDPROJECT"+i,0)));
        }
        //txtTest.setText("Nombre : "+nbProjects+" id : "+ids);
        viewAdapter.setJuryProjectList(juryProjectList);



    }

}