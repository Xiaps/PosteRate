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
import fr.eseo.mb.android.posterate.ui.projets.ProjectDetailActivity;
import fr.eseo.mb.android.posterate.ui.pseudoJury.MainPseudoJuryActivity;

public class PseudoJuryProjectListViewAdapter extends RecyclerView.Adapter<PseudoJuryProjectListViewAdapter.PseudoJuryProjectListViewHolder>{

    Activity activity;
    private ArrayList<Project> pseudoJuryProjectList;

    public PseudoJuryProjectListViewAdapter(MainPseudoJuryActivity activity){
        super();
        this.activity=activity;
        pseudoJuryProjectList = new ArrayList<>();
    }

    @Override
    public PseudoJuryProjectListViewAdapter.PseudoJuryProjectListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new PseudoJuryProjectListViewAdapter.PseudoJuryProjectListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PseudoJuryProjectListViewAdapter.PseudoJuryProjectListViewHolder holder, final int position){
        holder.posterTitle.setText(this.pseudoJuryProjectList.get(position).getTitle());
        holder.posterSupervisor.setText(this.pseudoJuryProjectList.get(position).getSupervisor().getSurname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProjectDetailActivity.class);
                intent.putExtra("MARKABLE",1);

                intent.putExtra("POSITION",Integer.parseInt(pseudoJuryProjectList.get(position).getProjectId()));
                //intent.putExtra("PROJETS",projectList);
                view.getContext().startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return pseudoJuryProjectList.size();
    }

    public void setPseudoJuryProjectList(ArrayList<Project> list){
        this.pseudoJuryProjectList =list;
        this.notifyDataSetChanged();
    }

    class PseudoJuryProjectListViewHolder extends RecyclerView.ViewHolder{
        final TextView posterTitle;
        final TextView posterSupervisor;
        public PseudoJuryProjectListViewHolder(@NonNull View itemView){

            super(itemView);
            this.posterTitle = itemView.findViewById(R.id.summary_project_title);
            this.posterSupervisor = itemView.findViewById(R.id.textView2);

        }
    }

}
