package fr.eseo.mb.android.posterate.ui.pseudoJury;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import fr.eseo.mb.android.posterate.R;
import fr.eseo.mb.android.posterate.controller.SaveOnFile;
import fr.eseo.mb.android.posterate.data.model.Jury;
import fr.eseo.mb.android.posterate.data.model.LoggedInUser;
import fr.eseo.mb.android.posterate.data.model.Project;
import fr.eseo.mb.android.posterate.viewAdapter.JuryProjectsListViewAdapter;
import fr.eseo.mb.android.posterate.viewAdapter.JurySummaryListViewAdapter;
import fr.eseo.mb.android.posterate.viewAdapter.ProjectSummaryListViewAdapter;
import fr.eseo.mb.android.posterate.viewAdapter.PseudoJuryProjectListViewAdapter;

public class MainPseudoJuryActivity extends AppCompatActivity {

    private PseudoJuryProjectListViewAdapter viewAdapter;
    private ArrayList<Project> projectList = ProjectSummaryListViewAdapter.projectList;
    private ArrayList<Project> pseudoJuryProjectList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoggedInUser.role="pseudoJury";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pseudo_jury);
        try {
            ProjectSummaryListViewAdapter.projectList = SaveOnFile.getProjets(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("=====================================");
        System.out.println(ProjectSummaryListViewAdapter.projectList.get(1).getTitle());
        System.out.println("=====================================");

        try {
            pseudoJuryProjectList = SaveOnFile.getProjetsSelection(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        RecyclerView recycler = findViewById(R.id.list_pseudoJury_project);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        recycler.setHasFixedSize(true);
        viewAdapter = new PseudoJuryProjectListViewAdapter(this);
        recycler.setAdapter(viewAdapter);

        viewAdapter.setPseudoJuryProjectList(pseudoJuryProjectList);

    }

}