package com.example.assignment;

import android.content.Context;
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


public class mylistadapter extends RecyclerView.Adapter<mylistadapter.ViewHolder> {
    public scorecards[] scorecards;

    // RecyclerView recyclerView;
    public mylistadapter(scorecards[] scorecards) {
        this.scorecards = scorecards;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Fresco.initialize(parent.getContext());
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //final scorecards myListData = scorecards[position];
        holder.t1.setText(scorecards[position].gett1());
        holder.t2.setText(scorecards[position].gett2());
        holder.t1flag.setImageURI(scorecards[position].gett1flag());
        holder.t2flag.setImageURI(scorecards[position].gett2flag());
        holder.starting_in.setText(scorecards[position].getStarting_in());
        holder.time.setText(scorecards[position].getTime());


        if(scorecards[position].getrate()!=null) {
            holder.rate.setText(scorecards[position].getrate());
            holder.rate2.setText(scorecards[position].getrate2());
            holder.rate_team.setText(scorecards[position].getrate_team());
        }
        else
        {
            holder.rectangle3.setVisibility(View.GONE);
            holder.rectangle4.setVisibility(View.GONE);
            holder.rectangle5.setVisibility(View.GONE);

            /*
            holder.rate.setText("0");
            holder.rate2.setText("0");
            holder.rate_team.setText("0");*/
        }





    }


    @Override
    public int getItemCount() {
        return scorecards.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView t1, t2, rate, rate2, rate_team, starting_in, time;
        public View rectangle3, rectangle4,rectangle5;
        SimpleDraweeView t1flag,t2flag;
        public TextView place;
        public CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            this.t1 = (TextView) itemView.findViewById(R.id.t1);
            this.t2 = (TextView) itemView.findViewById(R.id.t2);
            this.starting_in=(TextView) itemView.findViewById(R.id.starting_in);
            this.time= (TextView) itemView.findViewById(R.id.time);
            this.rectangle3=(View) itemView.findViewById(R.id.rate);
            this.rectangle4=(View) itemView.findViewById(R.id.rate2);
            this.rectangle5=(View) itemView.findViewById(R.id.rate_team);
            this.t1flag=(SimpleDraweeView) itemView.findViewById(R.id.t1flag);
            this.t2flag=(SimpleDraweeView) itemView.findViewById(R.id.t2flag);

            this.rate = (TextView) itemView.findViewById(R.id.rate);
            this.rate2 = (TextView) itemView.findViewById(R.id.rate2);
            this.rate_team = (TextView) itemView.findViewById(R.id.rate_team);

            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}