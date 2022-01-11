package com.example.assignment;

import android.telephony.VisualVoicemailService;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class AdapterForFinishedMatches extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final int LAYOUT_ONE = 1, LAYOUT_TWO = 2, LAYOUT_THREE = 3;
    ArrayList<ArrayList<String>> dataList;

    public AdapterForFinishedMatches(ArrayList<ArrayList<String>> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (dataList.get(position).get(0).charAt(0)) {
            case '1': return LAYOUT_ONE;
            case '2': return LAYOUT_TWO;
            case '3': return LAYOUT_THREE;
        }
        return super.getItemViewType(position);
    }

    // LAYOUT_ONE for match club on a date
    public static class ViewHolderForMatchDate extends RecyclerView.ViewHolder {
        TextView matchDate;
        public ViewHolderForMatchDate(@NonNull View itemView) {
            super(itemView);
            matchDate = itemView.findViewById(R.id.match_date_between_cards);
        }
    }

    // LAYOUT_TWO for finished match data card
    public static class ViewHolderForMatchData extends RecyclerView.ViewHolder {
        TextView matchVenue;
        SimpleDraweeView teamFlag1;
        TextView teamName1;
        TextView team1Score;
        TextView totalOversForTeam1;
        SimpleDraweeView teamFlag2;
        TextView teamName2;
        TextView team2Score;
        TextView totalOversForTeam2;
        TextView matchResults;
        TextView resultDetails;
        public ViewHolderForMatchData(@NonNull View itemView) {
            super(itemView);

            matchVenue = itemView.findViewById(R.id.match_venue);
            teamFlag1 = itemView.findViewById(R.id.team_flag_1);
            teamName1 = itemView.findViewById(R.id.team_name_1);
            team1Score = itemView.findViewById(R.id.team1score);
            totalOversForTeam1 = itemView.findViewById(R.id.total_overs_1);
            matchResults = itemView.findViewById(R.id.match_results);
            resultDetails = itemView.findViewById(R.id.results_details);
            teamFlag2 = itemView.findViewById(R.id.team_flag_2);
            teamName2 = itemView.findViewById(R.id.team_name_2);
            team2Score = itemView.findViewById(R.id.team2score);
            totalOversForTeam2 = itemView.findViewById(R.id.total_overs_2);
        }
    }

    // LAYOUT_THREE for finished all matches text
    public static class ViewHolderForAllMatchesText extends RecyclerView.ViewHolder {
        TextView allMatchesText;
        View cardBg;
        public ViewHolderForAllMatchesText(@NonNull View itemView) {
            super(itemView);
            allMatchesText = itemView.findViewById(R.id.all_matches_text);
            cardBg = itemView.findViewById(R.id.match_card);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        switch (viewType) {
            case LAYOUT_ONE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_date, parent, false);
                ViewHolderForMatchDate viewHolderForMatchDate = new ViewHolderForMatchDate(view);
                return viewHolderForMatchDate;
            case LAYOUT_TWO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.finished_match_card, parent, false);
                ViewHolderForMatchData viewHolderForMatchData = new ViewHolderForMatchData(view);
                return viewHolderForMatchData;
            case LAYOUT_THREE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_matches_layout, parent, false);
                ViewHolderForAllMatchesText viewHolderForAllMatchesText = new ViewHolderForAllMatchesText(view);
                return viewHolderForAllMatchesText;
            default:
                return null;
        }

        /*
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.finished_match_card, parent, false);
        // getting correct ViewHolder
        ViewHolderForMatchData viewHolderForMatchData = new ViewHolderForMatchData(view);

        return viewHolderForMatchData;
         */
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArrayList<String> data = dataList.get(position); // TODO extract data

        switch (holder.getItemViewType()) {
            case LAYOUT_ONE:
                ViewHolderForMatchDate viewHolderForMatchDate = (ViewHolderForMatchDate) holder;
                viewHolderForMatchDate.matchDate.setText(data.get(11));
                break;
            case LAYOUT_TWO:
                ViewHolderForMatchData viewHolderForMatchData = (ViewHolderForMatchData) holder;
                viewHolderForMatchData.teamName1.setText(data.get(1));
                viewHolderForMatchData.teamName2.setText(data.get(2));
                viewHolderForMatchData.teamFlag1.setImageURI(data.get(3));
                viewHolderForMatchData.teamFlag2.setImageURI(data.get(4));
                viewHolderForMatchData.team1Score.setText(data.get(5));
                viewHolderForMatchData.team2Score.setText(data.get(6));
                viewHolderForMatchData.totalOversForTeam1.setText(data.get(7));
                viewHolderForMatchData.totalOversForTeam2.setText(data.get(8));
                viewHolderForMatchData.matchResults.setText(data.get(9));
                viewHolderForMatchData.resultDetails.setText(data.get(10));
                break;
            case LAYOUT_THREE:
                ViewHolderForAllMatchesText viewHolderForAllMatchesText = (ViewHolderForAllMatchesText) holder;
                viewHolderForAllMatchesText.allMatchesText.setText("All Finished Matches");
                viewHolderForAllMatchesText.cardBg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(), "Showing all Finished Matches", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
        /*
        ViewHolderForMatchData viewHolderForMatchData = (ViewHolderForMatchData) holder;
        viewHolderForMatchData.matchVenue.setText("Parth Universal");
         */
    }

    @Override
    public int getItemCount() {
        Log.d("test", "get count running");
        return dataList.size();
    }
}
