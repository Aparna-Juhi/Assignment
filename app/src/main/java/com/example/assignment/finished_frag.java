package com.example.assignment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class finished_frag extends Fragment {

    Context mContext;
    static final String key = "data_transfer_key_for_bundling";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.finished_frag, container, false);
        RecyclerView finRecViu = v.findViewById(R.id.recycler_view_for_finished_matches);

        Log.d("test", "on create ok");
        finRecViu.setLayoutManager(new LinearLayoutManager(getMyContext()));
        //ArrayList<String> dataList = getArguments().getStringArrayList(key);
        ArrayList<ArrayList<String>> dataList = new ArrayList<>();
        AdapterForFinishedMatches adapter = new AdapterForFinishedMatches(dataList);
        getData(adapter);

        finRecViu.setAdapter(adapter);
        return v;
    }

    private Context getMyContext() {
        if(mContext == null)
            return getContext();
        return mContext;
    }

    public finished_frag newInstance(Object data) {

        finished_frag f = new finished_frag();
        Bundle args = new Bundle();

        // ArrayList<String> arrayList = new ArrayList<>();
        // we cannot pass an empty arrayList to Bundle
        // arrayList.add("a");
        Log.d("test", "newInstance ok");
        /*
        args.putStringArrayList(key, arrayList);
        f.setArguments(args);
         */

        return f;
    }

    private void getData(AdapterForFinishedMatches adapter) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.child("finished").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FinishedDataItem finishedDataItem = new FinishedDataItem();
                String key = "", team1Name = "", team2Name = "", team1Flag = "", team2Flag = "", date = "", score1 = "", score2 = "", overs1 = "", overs2 = "", winner = "", result = "";

                for(DataSnapshot snapshot1: snapshot.getChildren()) {

                    String Date = snapshot1.child("date").getValue().toString();

                    //changing date format
                    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                    Date dt1 = null;
                    try {
                        dt1 = format1.parse(Date);
                    } catch (Exception e) {
                        Log.d("error", "incorrect parsing of date");
                    }
                    DateFormat format2 = new SimpleDateFormat("EEEE");
                    String dayOfWeek = format2.format(dt1);
                    String month[] = {" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                    date = dayOfWeek + ", " + Integer.parseInt(Date.substring(Date.indexOf('/') + 1, Date.lastIndexOf('/'))) + " " + month[Integer.parseInt(Date.substring(0, Date.indexOf('/')))];

                    finishedDataItem.updateItem("1", team1Name, team2Name, team1Flag, team2Flag, score1, score2, overs1, overs2, winner, result, date);

                    DataSnapshot snapshot2 = snapshot1.child("m");
                    for (DataSnapshot match : snapshot2.getChildren()) {

                        MatchData matchData = match.getValue(MatchData.class);  //now working
                        // Points to note
                        // there must be a default constructor explicitly defined in MatchData class
                        // instance variables should be public and of the same name as key value
                        // only primitive datatypes allowed

                        finishedDataItem.updateItem("2", matchData.t1, matchData.t2, matchData.t1flag, matchData.t2flag, matchData.score1, matchData.score2, matchData.overs1, matchData.overs2, matchData.winner, matchData.result.substring(matchData.result.indexOf("by")), matchData.date);

                    }
                }

                finishedDataItem.updateItem("3", team1Name, team2Name, team1Flag, team2Flag, score1, score2, overs1, overs2, winner, result, date);

                adapter.dataList = finishedDataItem.getDataList();
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("test", "firebase error");
            }
        });




        /*
        RequestQueue queue = Volley.newRequestQueue(getMyContext());
        String url ="https://mocki.io/v1/2389d44c-81aa-4e04-bd2e-b8c7e17572c0";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArr = new JSONArray(response);
                            //scorecards = new scorecards[jsonArr.length()];
                            FinishedDataItem finishedDataItem = new FinishedDataItem();
                            String key ="", team1Name ="", team2Name ="", team1Flag ="", team2Flag ="", date ="", score1 ="", score2 ="", overs1 ="", overs2 ="", winner ="", result ="";

                            for (int i = 0; i < jsonArr.length(); i++)
                            {



                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                date = jsonObj.getString("date");
                                SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
                                Date dt1 = null;
                                try {
                                    dt1 = format1.parse(date);
                                }
                                catch (Exception e) {
                                    Log.d("error", "incorrect parsing of date");
                                }
                                DateFormat format2=new SimpleDateFormat("EEEE");
                                String dayOfWeek=format2.format(dt1);
                                String month[] = {" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                                date = dayOfWeek +", "+ Integer.parseInt(date.substring(date.indexOf('/')+1, date.lastIndexOf('/'))) +" "+ month[Integer.parseInt(date.substring(0, date.indexOf('/')))];

                                finishedDataItem.updateItem("1", team1Name, team2Name, team1Flag, team2Flag, score1, score2, overs1, overs2, winner, result, date);

                                JSONArray matchesArray= jsonObj.getJSONArray("m");

                                for(int j=0;j<matchesArray.length();j++) {
                                    JSONObject match = matchesArray.getJSONObject(j);

                                    team1Name = match.getString("t1");
                                    team2Name = match.getString("t2");
                                    team1Flag = match.getString("t1flag");
                                    team2Flag = match.getString("t2flag");
                                    date = match.getString("date");
                                    score1 = match.getString("score1");
                                    score2 = match.getString("score2");
                                    overs1 = match.getString("overs1");
                                    overs2 = match.getString("overs2");
                                    winner = match.getString("winner");
                                    winner = winner + " Won";
                                    result = match.getString("result");
                                    result = result.substring(result.indexOf("by"));

                                    JSONObject matchIn3h = match.has("odds")?match.getJSONObject("odds"):null;

                                    finishedDataItem.updateItem("2", team1Name, team2Name, team1Flag, team2Flag, score1, score2, overs1, overs2, winner, result, date);
                                }


                            }
                            finishedDataItem.updateItem("3", team1Name, team2Name, team1Flag, team2Flag, score1, score2, overs1, overs2, winner, result, date);

                            adapter.dataList = finishedDataItem.getDataList();
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            Log.e("error message", ""+e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "error in reading from Volley");
            }
        });;
        queue.add(stringRequest);
        */

    }

}

class MatchData {
    public String date, match_no, overs1, overs2, result, score1, score2;
    public long t;
    public String t1, t1flag, t2, t2flag, winner;

    public MatchData(String date, String match_no, String overs1, String overs2, String result, String score1, String score2, long t, String t1, String t1flag, String t2, String t2flag, String winner) {
        this.date = date;
        this.match_no = match_no;
        this.overs1 = overs1;
        this.overs2 = overs2;
        this.result = result;
        this.score1 = score1;
        this.score2 = score2;
        this.t = t;
        this.t1 = t1;
        this.t1flag = t1flag;
        this.t2 = t2;
        this.t2flag = t2flag;
        this.winner = winner + " Won";
    }

    public MatchData() {

    }
}

