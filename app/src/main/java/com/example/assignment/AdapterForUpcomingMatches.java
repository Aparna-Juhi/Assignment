package com.example.assignment;

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

public class AdapterForUpcomingMatches extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //getting dataList from api
    final int LAYOUT_ONE = 1, LAYOUT_TWO = 2, LAYOUT_THREE = 3, LAYOUT_FOUR = 4;
    ArrayList<ArrayList<String>> dataList;

    public AdapterForUpcomingMatches(ArrayList<ArrayList<String>> dataList) {
        Log.d("check", ""+dataList.size());
        this.dataList = dataList;
    }

    //very necessary here (setting the type of layout to be inflated)
    @Override
    public int getItemViewType(int position) {
        //generate specific view required according to the data
        Log.d("check", "hel "+dataList.size());
        switch (dataList.get(position).get(0).charAt(0)) {
            case '1': return LAYOUT_ONE;
            case '2': return LAYOUT_TWO;
            case '3': return LAYOUT_THREE;
            case '4': return LAYOUT_FOUR;
        }
        return super.getItemViewType(position);
    }



    // LAYOUT_ONE -> for match club data
    public static class ViewHolderForMatchDate extends RecyclerView.ViewHolder {
        TextView matchDate;
        public ViewHolderForMatchDate(@NonNull View itemView) {
            super(itemView);
            matchDate = itemView.findViewById(R.id.match_date_between_cards);
        }
    }

    // LAYOUT_TWO -> match in 3h
    public static class ViewHolderForMatchIn3h extends RecyclerView.ViewHolder {
        TextView teamNameForData;
        TextView oddsInFavour;
        TextView oddsAgainst;
        TextView teamName1;
        TextView teamName2;
        TextView matchVenue;
        TextView matchTimeLessThan3h;
        //TODO check flags
        SimpleDraweeView teamFlag1;
        SimpleDraweeView teamFlag2;

        public ViewHolderForMatchIn3h(@NonNull View itemView) {
            super(itemView);

            matchVenue = itemView.findViewById(R.id.match_venue);
            teamName1 = itemView.findViewById(R.id.team_name_1);
            teamName2 = itemView.findViewById(R.id.team_name_2);
            matchTimeLessThan3h = itemView.findViewById(R.id.match_in_hm);
            teamNameForData = itemView.findViewById(R.id.team_name_for_data);
            oddsInFavour = itemView.findViewById(R.id.odds_in_favour);
            oddsAgainst = itemView.findViewById(R.id.odds_against);

            //TODO insert flags
            teamFlag1 = itemView.findViewById(R.id.team_flag_1);
            teamFlag2 = itemView.findViewById(R.id.team_flag_2);
        }
    }

    // LAYOUT_THREE -> match on a date
    public static class ViewHolderForMatchOnDate extends RecyclerView.ViewHolder {
        TextView matchVenue;
        TextView teamName1;
        TextView teamName2;
        TextView startTime;
        TextView startDate;

        //TODO check flags
        SimpleDraweeView teamFlag1;
        SimpleDraweeView teamFlag2;

        public ViewHolderForMatchOnDate(@NonNull View itemView) {
            super(itemView);
            matchVenue = itemView.findViewById(R.id.match_venue);
            teamName1 = itemView.findViewById(R.id.team_name_1);
            teamName2 = itemView.findViewById(R.id.team_name_2);
            startTime = itemView.findViewById(R.id.start_time_of_match_at_date);
            startDate = itemView.findViewById(R.id.match_date);

            teamFlag1 = itemView.findViewById(R.id.team_flag_1);
            teamFlag2 = itemView.findViewById(R.id.team_flag_2);
        }
    }

    // LAYOUT_FOUR -> last layout for all matches
    public static class ViewHolderForAllMatches extends RecyclerView.ViewHolder {
        TextView all_matches_text;
        public ViewHolderForAllMatches(@NonNull View itemView) {
            super(itemView);
            all_matches_text = itemView.findViewById(R.id.all_matches_text);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("check", "running onCreate");
        View view = null;
        switch (viewType) {
            case LAYOUT_ONE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_date, parent, false);
                // get a viewHolder object passing the view to ViewHolderForMatchIn3h constructor
                ViewHolderForMatchDate viewHolderForMatchDate = new ViewHolderForMatchDate(view);
                return viewHolderForMatchDate;

            case LAYOUT_TWO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.background_card_for_match_in_3h, parent, false);
                // get a viewHolder object passing the view to ViewHolderForMatchIn3h constructor
                ViewHolderForMatchIn3h viewHolderForMatchIn3h = new ViewHolderForMatchIn3h(view);
                return viewHolderForMatchIn3h;

            case LAYOUT_THREE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.background_card_for_match_on_date, parent, false);
                // get a viewHolder object passing the view to ViewHolderForMatchIn3h constructor
                ViewHolderForMatchOnDate viewHolderForMatchOnDate = new ViewHolderForMatchOnDate(view);
                return viewHolderForMatchOnDate;

            case LAYOUT_FOUR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_matches_layout, parent, false);
                // get a viewHolder object passing the view to ViewHolderForMatchIn3h constructor
                ViewHolderForAllMatches viewHolderForAllMatches = new ViewHolderForAllMatches(view);
                return viewHolderForAllMatches;

            default:
                return null;
        }

        /*
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.background_card_for_match_in_3h, parent, false);
        // get a viewHolder object passing the view to ViewHolderForMatchIn3h constructor
        ViewHolderForMatchIn3h viewHolderForMatchIn3h = new ViewHolderForMatchIn3h(view);
        return viewHolderForMatchIn3h;
         */
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArrayList<String> data = dataList.get(position);
        switch (holder.getItemViewType()) {
            case LAYOUT_ONE:
                ViewHolderForMatchDate viewHolderForMatchDate= (ViewHolderForMatchDate)holder;
                viewHolderForMatchDate.matchDate.setText(data.get(5));
                break;
            case LAYOUT_TWO:
                ViewHolderForMatchIn3h viewHolderForMatchIn3h = (ViewHolderForMatchIn3h) holder;
                viewHolderForMatchIn3h.teamNameForData.setText(data.get(9));
                viewHolderForMatchIn3h.oddsAgainst.setText(data.get(8));
                viewHolderForMatchIn3h.oddsInFavour.setText(data.get(7));
                viewHolderForMatchIn3h.matchTimeLessThan3h.setText(data.get(6));
                viewHolderForMatchIn3h.teamName2.setText(data.get(2));
                viewHolderForMatchIn3h.teamName1.setText(data.get(1));
                viewHolderForMatchIn3h.teamFlag1.setImageURI(data.get(3));
                viewHolderForMatchIn3h.teamFlag2.setImageURI(data.get(4));
                break;
            case LAYOUT_THREE:
                ViewHolderForMatchOnDate viewHolderForMatchOnDate = (ViewHolderForMatchOnDate) holder;
                viewHolderForMatchOnDate.teamName1.setText(data.get(1));
                viewHolderForMatchOnDate.teamName2.setText(data.get(2));
                viewHolderForMatchOnDate.teamFlag1.setImageURI(data.get(3));
                viewHolderForMatchOnDate.teamFlag2.setImageURI(data.get(4));
                viewHolderForMatchOnDate.startTime.setText(data.get(6));
                viewHolderForMatchOnDate.startDate.setText(data.get(5));
                break;
            case LAYOUT_FOUR:
                ViewHolderForAllMatches viewHolderForAllMatches = (ViewHolderForAllMatches) holder;
                viewHolderForAllMatches.all_matches_text.setText("All Upcoming Matches");
                break;
        }

        /*
        unused code
        ViewHolderForMatchIn3h viewHolderForMatchIn3h = (ViewHolderForMatchIn3h)holder;
        viewHolderForMatchIn3h.teamNameForData.setText("SriLanka");

        Log.d("check", "running onBind");
         */
    }

    @Override
    public int getItemCount() {
        Log.d("check", "running getItemCount"+dataList.size());
        return dataList.size();
    }
}
