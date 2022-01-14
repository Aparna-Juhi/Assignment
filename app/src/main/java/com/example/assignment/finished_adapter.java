package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class finished_adapter extends RecyclerView.Adapter<finished_adapter.ViewHolder> {
    public ArrayList<finished_scorecards> finished_scorecards= new ArrayList<>() ;


    // RecyclerView recyclerView;
    public finished_adapter(ArrayList<finished_scorecards>  finished_scorecards) {
        this.finished_scorecards = finished_scorecards;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Fresco.initialize(parent.getContext());
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.finished_match_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // final finished_scorecards myListData = finished_scorecards[position];
        holder.team_name_1.setText(finished_scorecards.get(position).getTeam_name_1());
        holder.team_name_2.setText(finished_scorecards.get(position).getTeam_name_2());
        holder.match_result.setText(finished_scorecards.get(position).getMatch_result());

        holder.team1score.setText(finished_scorecards.get(position).getTeam1score());
        holder.team2score.setText(finished_scorecards.get(position).getTeam2score());
        holder.total_overs_1.setText(finished_scorecards.get(position).getTotal_overs_1());
        holder.total_overs_2.setText(finished_scorecards.get(position).getTotal_overs_2());
        holder.result_details.setText(finished_scorecards.get(position).getResult_details());
        holder.team_flag_1.setImageURI(finished_scorecards.get(position).getTeam_flag_1());
        holder.team_flag_2.setImageURI(finished_scorecards.get(position).getTeam_flag_2());
    }


    @Override
    public int getItemCount() {
        return finished_scorecards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView match_result, result_details, team_name_1, team_name_2;
        public TextView team1score, team2score, total_overs_1, total_overs_2;
        SimpleDraweeView team_flag_1, team_flag_2;

        public ViewHolder(View itemView) {
            super(itemView);
            this.match_result = (TextView)  itemView.findViewById(R.id.match_results);
           // this.match_venue = (TextView)  itemView.findViewById(R.id.match_venue);
            this.result_details = (TextView)  itemView.findViewById(R.id.results_details);
            this.team_flag_1 = (SimpleDraweeView)  itemView.findViewById(R.id.team_flag_1);
            this.team_flag_2 = (SimpleDraweeView)  itemView.findViewById(R.id.team_flag_2);
            this.team_name_1 = (TextView)  itemView.findViewById(R.id.team_name_1);
            this.team_name_2 = (TextView)  itemView.findViewById(R.id.team_name_2);
            this.team1score = (TextView)  itemView.findViewById(R.id.team1score);
            this.team2score = (TextView)  itemView.findViewById(R.id.team2score);
            this.total_overs_1 = (TextView)  itemView.findViewById(R.id.total_overs_1);
            this.total_overs_2 = (TextView)  itemView.findViewById(R.id.total_overs_2);;
        }
    }
}
