package com.example.assignment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FinishedFragArrayList extends Fragment {
    private Context mContext;
    private Context getMyContext(){
        if(mContext == null)
            mContext = getContext();
        return mContext;
    }
    ArrayList<finished_scorecards>finished_scorecards_array= new ArrayList<finished_scorecards>();

    finished_adapter finished_adapter;
    DatabaseReference myRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.finished_frag, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.finished_recyclerview);
       // finished_scorecards_array = new finished_scorecards[0];
        finished_scorecards_array= new ArrayList<finished_scorecards>(0);
        finished_adapter = new finished_adapter(finished_scorecards_array);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext()));
        recyclerView.setAdapter(finished_adapter);

        //Adding Firebase
        myRef= FirebaseDatabase.getInstance().getReference();
        myRef.child("finished").addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if(snapshot.exists()) {
                    int i=0;

                    for (DataSnapshot dateSnapShot : snapshot.getChildren()) {
                        for (DataSnapshot matches_data : dateSnapShot.child("m").getChildren()) {
                            Map<String, String> td = (HashMap<String, String>) matches_data.getValue();
                            // Toast.makeText(getMyContext(), td.toString(), Toast.LENGTH_SHORT).show();
                            // Log.d("map", td.toString());

                            finished_scorecards Fin= new finished_scorecards(
                                    td.getOrDefault("winner", ""),
                                    td.getOrDefault("result", "null"),
                                    td.getOrDefault("t1flag", ""),
                                    td.getOrDefault("t2flag", ""),
                                    td.getOrDefault("t1", ""),
                                    td.getOrDefault("t2", ""),
                                    td.getOrDefault("score1", ""),
                                    td.getOrDefault("score2", ""),
                                    td.getOrDefault("overs1", ""),
                                    td.getOrDefault("overs2", ""));
                            finished_scorecards_array.add(Fin);

                        }
                    }


                }
                finished_adapter.finished_scorecards = finished_scorecards_array;
                finished_adapter.notifyDataSetChanged();




            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return v;
    }
    public static FinishedFragArrayList newInstance() {

        FinishedFragArrayList f = new FinishedFragArrayList();
        return f;
    }
}

