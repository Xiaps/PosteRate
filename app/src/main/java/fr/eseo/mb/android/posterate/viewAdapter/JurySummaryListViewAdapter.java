package fr.eseo.mb.android.posterate.viewAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.eseo.mb.android.posterate.R;
import fr.eseo.mb.android.posterate.data.model.Jury;
import fr.eseo.mb.android.posterate.ui.jury.JuryFragment;
import fr.eseo.mb.android.posterate.ui.jury.JuryProjectActivity;

public class JurySummaryListViewAdapter extends RecyclerView.Adapter<JurySummaryListViewAdapter.JurySummaryListViewHolder>{


    private JuryFragment fragment;

    static public ArrayList<Jury> juryList;


    public JurySummaryListViewAdapter(JuryFragment fragment){
        super();
        this.fragment = fragment;
        juryList = new ArrayList<>();
    }

    @Override
    public JurySummaryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new JurySummaryListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.jury_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final JurySummaryListViewHolder holder, final int position){
        String juryNamesStr = "";
        for(int i=0 ;i<this.juryList.get(position).getMembers().size();i++){
            juryNamesStr+=this.juryList.get(position).getMembers().get(i).getFullName()+ " ";
        }
        holder.juryNames.setText(juryNamesStr);
        holder.juryDate.setText(this.juryList.get(position).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), JuryProjectActivity.class);
                intent.putExtra("NBPROJECT", juryList.get(position).getProjects().size());
                for(int i=0;i<juryList.get(position).getProjects().size();i++){
                    intent.putExtra("IDPROJECT"+i,Integer.parseInt(juryList.get(position).getProjects().get(i).getProjectId()));
                    System.out.println("======================================");
                    System.out.println(juryList.get(position).getProjects().get(i).getProjectId());
                    System.out.println("======================================");}
                intent.putExtra("POSITION",position);
                view.getContext().startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return this.juryList.size();
    }

    class JurySummaryListViewHolder extends RecyclerView.ViewHolder {
        final TextView juryNames;
        final TextView juryDate;

        public JurySummaryListViewHolder(@NonNull View itemView){
            super(itemView);
            this.juryNames = itemView.findViewById(R.id.jury_names);
            this.juryDate = itemView.findViewById(R.id.jury_date);
        }
    }

    public void setJuryList(ArrayList<Jury> juryList){
        this.juryList=juryList;
        this.notifyDataSetChanged();
    }




}
