package fr.eseo.mb.android.posterate.ui.projets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

import fr.eseo.mb.android.posterate.R;
import fr.eseo.mb.android.posterate.controller.AsynchTaskPosterFullPic;
import fr.eseo.mb.android.posterate.controller.AsynchTaskPosterPic;
import fr.eseo.mb.android.posterate.data.model.LoggedInUser;

import static fr.eseo.mb.android.posterate.viewAdapter.ProjectSummaryListViewAdapter.projectList;

public class FullPosterActivity extends AppCompatActivity {

    ImageView fullPosterView;

    int position;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        position = getIntent().getIntExtra("POSITION", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_poster);
        try {
            new AsynchTaskPosterFullPic(this).execute(new URL("https://172.24.5.16/pfe/webservice.php?q=POSTR&user="+ LoggedInUser.getDisplayName()+"&proj="+projectList.get(position).getProjectId()+"&style="+"FLB64"+"&token="+ LoggedInUser.token));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public void updatePoster(Bitmap results) {
        fullPosterView = findViewById(R.id.full_poster_view);
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
            this.fullPosterView.setImageResource(R.drawable.ic_e_noir);
            return;
        }
        Log.d("Poster", "updating to bitmap " + results.toString());
        this.fullPosterView.setImageBitmap(results);

    }



}