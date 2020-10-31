package fr.eseo.mb.android.posterate.viewAdapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.eseo.mb.android.posterate.R;
import fr.eseo.mb.android.posterate.data.model.Project;
import fr.eseo.mb.android.posterate.ui.jury.JuryProjectActivity;
import fr.eseo.mb.android.posterate.ui.projets.ProjectDetailActivity;

public class JuryProjectsListViewAdapter extends RecyclerView.Adapter<JuryProjectsListViewAdapter.JuryProjectsListViewHolder> {

    Activity activity;
    private ArrayList<Project> juryProjectList;

    public JuryProjectsListViewAdapter(JuryProjectActivity activity){
        super();
        this.activity=activity;
        juryProjectList = new ArrayList<>();
    }

    @Override
    public JuryProjectsListViewAdapter.JuryProjectsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new JuryProjectsListViewAdapter.JuryProjectsListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final JuryProjectsListViewAdapter.JuryProjectsListViewHolder holder, final int position){
        holder.posterTitle.setText(this.juryProjectList.get(position).getTitle());
        holder.posterSupervisor.setText(this.juryProjectList.get(position).getSupervisor().getSurname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProjectDetailActivity.class);
                intent.putExtra("MARKABLE",1);
                intent.putExtra("POSITION",Integer.parseInt(juryProjectList.get(position).getProjectId()));
                //intent.putExtra("PROJETS",projectList);
                view.getContext().startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return juryProjectList.size();
    }

    public void setJuryProjectList(ArrayList<Project> list){
        this.juryProjectList=list;
        this.notifyDataSetChanged();
    }

    class JuryProjectsListViewHolder extends RecyclerView.ViewHolder{
        final TextView posterTitle;
        final TextView posterSupervisor;
        public JuryProjectsListViewHolder(@NonNull View itemView){

            super(itemView);
            this.posterTitle = itemView.findViewById(R.id.summary_project_title);
            this.posterSupervisor = itemView.findViewById(R.id.textView2);

        }
    }

}
