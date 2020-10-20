package fr.eseo.mb.android.posterate.viewAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.eseo.mb.android.posterate.R;
import fr.eseo.mb.android.posterate.data.model.LoggedInUser;
import fr.eseo.mb.android.posterate.data.model.Project;
import fr.eseo.mb.android.posterate.ui.projets.ProjectDetailActivity;
import fr.eseo.mb.android.posterate.ui.projets.ProjetsFragment;

public class ProjectSummaryListViewAdapter extends RecyclerView.Adapter<ProjectSummaryListViewAdapter.ProjectSummaryListViewHolder>{


    private ProjetsFragment fragment;

    static public ArrayList<Project> projectList;


    public ProjectSummaryListViewAdapter(ProjetsFragment fragment){
        super();
        this.fragment = fragment;
        projectList = new ArrayList<>();
    }

    @Override
    public ProjectSummaryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new ProjectSummaryListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ProjectSummaryListViewHolder holder, final int position){
        holder.posterTitle.setText(this.projectList.get(position).getTitle());
        holder.posterSupervisor.setText(this.projectList.get(position).getSupervisor().getSurname());

        if(canBeShown(this.projectList.get(position))){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ProjectDetailActivity.class);
                    intent.putExtra("POSITION",position);
                    //intent.putExtra("PROJETS",projectList);
                    view.getContext().startActivity(intent);
                }
            });
        }else {
            holder.posterTitle.setText(this.projectList.get(position).getTitle()+"  CONFIDENTIAL");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(view.getContext(), "Ce projet est confidentiel...", 4);
                    toast.show();
                }
            });
        }



    }

    public int getItemCount(){
        return this.projectList.size();
    }

    class ProjectSummaryListViewHolder extends RecyclerView.ViewHolder {
        final TextView posterTitle;
        final TextView posterSupervisor;

        public ProjectSummaryListViewHolder(@NonNull View itemView){
            super(itemView);
            this.posterTitle = itemView.findViewById(R.id.summary_project_title);
            this.posterSupervisor = itemView.findViewById(R.id.textView2);
        }
    }

    public void setProjectList(ArrayList<Project> projectList){
        this.projectList=projectList;
        System.out.print("=================================================");
        System.out.println("NOMBRE PROJET : "+projectList.size());
        for(int i=0;i<projectList.size();i++){
            System.out.println(projectList.get(i).getConfid());
        }
        System.out.print("=================================================");
        this.notifyDataSetChanged();


    }

    public boolean canBeShown(Project project) {
        if(project.getConfid()<1) {
            return true;
        } else {
            if(project.getSupervisor().getFullName().equals(LoggedInUser.getFullName())) {
                return true;
            }
        }
        return false;
    }




}
