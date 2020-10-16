package fr.eseo.mb.android.posterate.ui.jury;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import fr.eseo.mb.android.posterate.R;
import fr.eseo.mb.android.posterate.data.model.Jury;
import fr.eseo.mb.android.posterate.viewAdapter.JurySummaryListViewAdapter;
import fr.eseo.mb.android.posterate.viewAdapter.ProjectSummaryListViewAdapter;

public class JuryProjectActivity extends AppCompatActivity {

    private ProjectSummaryListViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_projets);
        ArrayList<Jury> juryList = JurySummaryListViewAdapter.juryList;

    }
}