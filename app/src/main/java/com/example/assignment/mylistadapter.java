package com.example.assignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;


public class mylistadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public scorecards[] scorecards;
    private RecyclerView.ViewHolder holder;
    private int position;
    private  long t, difference_in_timestamps;

    // RecyclerView recyclerView;
    public mylistadapter(scorecards[] scorecards) {
        this.scorecards = scorecards;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("msg", "getItemtype");
        //generate specific view required according to the data
        if(scorecards.length > position)
        switch (scorecards[position].getKey()) {
            case 1:
                return 1;
            case 2:
                return 2;

        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        Log.d("msg", "oncreate");

        switch (viewType) {
            case 1:
                Log.d("msg", "case1");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                // get a viewHolder object passing the view to ViewHolderForMatchIn3h constructor
                ViewHolder viewHolderForMatchDate = new ViewHolder(view);
                return viewHolderForMatchDate;
            case 2:
                Log.d("msg", "case2");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_upcoming_matches, parent, false);
                // get a viewHolder object passing the view to ViewHolderForMatchIn3h constructor
                ViewHolderUpcomingAll viewHolderForUpcomingAll = new ViewHolderUpcomingAll(view);
                return viewHolderForUpcomingAll;
            default:
                return null;


        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("msg", "onBind");
        switch (holder.getItemViewType()) {
            case 1:
                ViewHolder viewHolderForMatchDate = (ViewHolder) holder;
                viewHolderForMatchDate.t1.setText(scorecards[position].gett1());
                viewHolderForMatchDate.t2.setText(scorecards[position].gett2());
                viewHolderForMatchDate.t1flag.setImageURI(scorecards[position].gett1flag());
                viewHolderForMatchDate.t2flag.setImageURI(scorecards[position].gett2flag());


                long time_of_match=Long.parseLong(scorecards[position].getStarting_in());
                SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm aa");
                SimpleDateFormat formatDate=new SimpleDateFormat("d MMM ");
                long system_time=System.currentTimeMillis();
//                long system_time=10400000;

                difference_in_timestamps = time_of_match - system_time;
                Log.d("msg", "running  "+difference_in_timestamps);
                if((time_of_match - system_time) <= 10800000 && (time_of_match - system_time)>=0) {
                    Log.d("if case", ""+difference_in_timestamps);



                    viewHolderForMatchDate.starting_in.setText("Starting In");
                   // t= difference_in_timestamps;
                    new CountDownTimer(difference_in_timestamps, 1000)
                    {
                        @Override
                        public void onTick(long l) {
                            String time = formatTime.format(l);
                           // Log.d("msg", "ticking : "+time);
                            viewHolderForMatchDate.time.setText(time);

                        }

                        @Override
                        public void onFinish() {
                            Log.d("msg", "Finish");

                        }
                    }.start();
                }
                else {
                    String time = formatTime.format(
                            time_of_match);
                    String date = formatDate.format(time_of_match);
                    viewHolderForMatchDate.starting_in.setText(time);
                    viewHolderForMatchDate.time.setText(date);
                }



                if (scorecards[position].getrate() != null) {
                    viewHolderForMatchDate.rate.setText(scorecards[position].getrate());
                    viewHolderForMatchDate.rate2.setText(scorecards[position].getrate2());
                    viewHolderForMatchDate.rate_team.setText(scorecards[position].getrate_team());
                } else {
                    viewHolderForMatchDate.rectangle3.setVisibility(View.GONE);
                    viewHolderForMatchDate.rectangle4.setVisibility(View.GONE);
                    viewHolderForMatchDate.rectangle5.setVisibility(View.GONE);


                }
                break;


            case 2:
                ViewHolderUpcomingAll viewHolderForUpcomingAll = (ViewHolderUpcomingAll) holder;
                viewHolderForUpcomingAll.textView.setText("All Upcoming Matches");


        }
    }

    @Override
    public int getItemCount() {
        Log.d("msg", "count");
        if(scorecards==null)
            return 0;
        return scorecards.length;
    }

    //very necessary here (setting the type of layout to be inflated)


    //View holder for upcoming matches

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView t1, t2, rate, rate2, rate_team, starting_in, time;
        public View rectangle3, rectangle4, rectangle5;
        SimpleDraweeView t1flag, t2flag;
        public TextView place;
        public CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            this.t1 = (TextView) itemView.findViewById(R.id.t1);
            this.t2 = (TextView) itemView.findViewById(R.id.t2);
            this.starting_in = (TextView) itemView.findViewById(R.id.starting_in);
            this.time = (TextView) itemView.findViewById(R.id.time);
            this.rectangle3 = (View) itemView.findViewById(R.id.rate);
            this.rectangle4 = (View) itemView.findViewById(R.id.rate2);
            this.rectangle5 = (View) itemView.findViewById(R.id.rate_team);
            this.t1flag = (SimpleDraweeView) itemView.findViewById(R.id.t1flag);
            this.t2flag = (SimpleDraweeView) itemView.findViewById(R.id.t2flag);

            this.rate = (TextView) itemView.findViewById(R.id.rate);
            this.rate2 = (TextView) itemView.findViewById(R.id.rate2);
            this.rate_team = (TextView) itemView.findViewById(R.id.rate_team);

            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }


    //View holder for all upcoming messages
    public static class ViewHolderUpcomingAll extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolderUpcomingAll(@NonNull View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.upcoming_matches_setText);


        }
    }

}