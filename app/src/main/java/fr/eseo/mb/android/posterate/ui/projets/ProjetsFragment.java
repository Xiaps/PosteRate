package fr.eseo.mb.android.posterate.ui.projets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import fr.eseo.mb.android.posterate.R;
import fr.eseo.mb.android.posterate.controller.AsynchTaskProjet;
import fr.eseo.mb.android.posterate.data.model.LoggedInUser;
import fr.eseo.mb.android.posterate.data.model.Project;
import fr.eseo.mb.android.posterate.viewAdapter.ProjectSummaryListViewAdapter;

public class ProjetsFragment extends Fragment {

    private ProjetsViewModel projetsViewModel;
    private ProjectSummaryListViewAdapter viewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        projetsViewModel =
                ViewModelProviders.of(this).get(ProjetsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_projets, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        projetsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recycler = view.findViewById(R.id.fragment_project_list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        recycler.setHasFixedSize(true);
        viewAdapter = new ProjectSummaryListViewAdapter(this);
        recycler.setAdapter(viewAdapter);

        try {
            new AsynchTaskProjet(this).execute(new URL("https://172.24.5.16/pfe/webservice.php?q=LIPRJ&user="+LoggedInUser.getDisplayName()+"&token="+ LoggedInUser.token));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void setProjectList(ArrayList<Project> projectList){
        viewAdapter.setProjectList(projectList);

    }



}