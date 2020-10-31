package fr.eseo.mb.android.posterate.ui.projets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import fr.eseo.mb.android.posterate.R;
import fr.eseo.mb.android.posterate.controller.AsynchTaskPosterPic;
import fr.eseo.mb.android.posterate.data.model.LoggedInUser;
import fr.eseo.mb.android.posterate.data.model.Project;
import fr.eseo.mb.android.posterate.viewAdapter.ProjectSummaryListViewAdapter;

public class ProjectDetailActivity extends AppCompatActivity {

    ImageView posterDetailImage;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Project> projectList = ProjectSummaryListViewAdapter.projectList;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        position = getIntent().getIntExtra("POSITION", 0);
        int markable = getIntent().getIntExtra("MARKABLE",0);
        TextView projectDetailTitle = findViewById(R.id.poster_detail_title);
        TextView projectDetailDesc = findViewById(R.id.poster_detail_desc);
        TextView projectDetailStudents = findViewById(R.id.poster_detail_students);
        TextView projectDetailSupervisor = findViewById(R.id.poster_detail_super);
        ImageView posterDetailImage = findViewById(R.id.poster_detail_image);
        TextView textViewNote = findViewById(R.id.textViewNote);
        Button validerNote = findViewById(R.id.validerNote);
        EditText note = findViewById(R.id.note);

        projectDetailTitle.setText(projectList.get(position).getTitle());
        projectDetailDesc.setText(projectList.get(position).getDescrip().substring(0,500)+"...");

        System.out.println("=================================================================");
        System.out.println(markable);
        System.out.println("=================================================================");

        if(markable==1){
            textViewNote.setVisibility(View.VISIBLE);
            validerNote.setVisibility(View.VISIBLE);
            note.setVisibility(View.VISIBLE);
        }else{
            textViewNote.setVisibility(View.INVISIBLE);
            validerNote.setVisibility(View.INVISIBLE);
            note.setVisibility(View.INVISIBLE);
        }

        String students = "";
        for (int i = 0; i < projectList.get(position).getStudents().size(); i++) {
            students += projectList.get(position).getStudents().get(i).getSurname();
            students += " " + projectList.get(position).getStudents().get(i).getForename() + "  ";
        }

        projectDetailStudents.setText(students);
        projectDetailSupervisor.setText(projectList.get(position).getSupervisor().getForename() + " " + projectList.get(position).getSupervisor().getSurname());

        try {
            new AsynchTaskPosterPic(this).execute(new URL("https://172.24.5.16/pfe/webservice.php?q=POSTR&user="+LoggedInUser.getDisplayName()+"&proj="+projectList.get(position).getProjectId()+"&style="+"THB64"+"&token="+ LoggedInUser.token));
            System.out.println("============================================================");
            System.out.println("https://172.24.5.16/pfe/webservice.php?q=POSTR&user="+ LoggedInUser.getDisplayName()+"&proj="+projectList.get(position).getProjectId()+"&style="+"FLB64"+"&token="+ LoggedInUser.token);
            System.out.println("============================================================");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void updatePoster(Bitmap results) {
        posterDetailImage = findViewById(R.id.poster_detail_image);
        if (results == null) {
            Log.d("Poster", "Nothing returned");
        } else {
            Log.d("Poster", "Something returned");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Updating poster: ");
        sb.append(results);
        Log.d("update Poster", sb.toString() == null ? "icon" : "poster");
        if (results == null) {
            Log.d("Poster", "updating to icon");
            this.posterDetailImage.setImageResource(R.drawable.ic_e_noir);
            return;
        }
        Log.d("Poster", "updating to bitmap " + results.toString());
        this.posterDetailImage.setImageBitmap(results);
        posterDetailImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),FullPosterActivity.class);
                intent.putExtra("POSITION",position);
                v.getContext().startActivity(intent);
                Toast toast = Toast.makeText(v.getContext(), "Chargement en cours ...", 4);
                toast.show();
            }
        });
    }


}