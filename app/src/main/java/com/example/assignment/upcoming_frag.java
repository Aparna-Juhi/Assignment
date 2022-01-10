package com.example.assignment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class upcoming_frag extends Fragment {

    Context mContext;
    ArrayList<ArrayList<String>> dataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.upcoming_frag, container, false);
        RecyclerView upcRecViu = v.findViewById(R.id.recycler_view_for_upcoming_matches);
        upcRecViu.setLayoutManager(new LinearLayoutManager(getMyContext()));
        Log.d("check", "onCreateRunning");
        //ArrayList<ArrayList<String>> dataList= getArguments().getStringArrayList("transfer_to_new_fragment");
        AdapterForUpcomingMatches adapter = new AdapterForUpcomingMatches(dataList);
        //upcRecViu.setHasFixedSize(true);
        upcRecViu.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

        return v;
    }

    private Context getMyContext(){
        if(mContext == null)
            mContext = getContext();
        return mContext;
    }

    public static upcoming_frag newInstance(Object data) {

        upcoming_frag f = new upcoming_frag();
        Bundle args = new Bundle();

        new upcoming_frag().getData(); //TODO get from data

        //args.putStringArrayList("transfer_to_new_fragment", new upcoming_frag().dataList);
        //f.setArguments(args);

        return f;
    }


     void getData() {
        RequestQueue queue = Volley.newRequestQueue(getMyContext());
        String url ="https://mocki.io/v1/30786c0a-390e-41d5-9ad8-549ed26cba64";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray jsonArr = new JSONArray(response);
                            //scorecards = new scorecards[jsonArr.length()];
                            UpcomingDataItem upcomingDataItem = new UpcomingDataItem();
                            String key ="", team1Name ="", team2Name ="", team1Flag ="", team2Flag ="", date ="", timeStamp ="", oddsInFavour ="", oddsAgainst ="", rateTeamName = "";

                            for (int i = 0; i < jsonArr.length(); i++)
                            {

                                /*
                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                scorecards[i]=new scorecards(jsonObj.getString("date"), "Delhi");
                                */

                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                date = jsonObj.getString("date");

                                upcomingDataItem.updateItem("1", team1Name, team2Name, team1Flag, team2Flag, date, timeStamp, oddsInFavour, oddsAgainst, rateTeamName);

                                JSONArray matchesArray= jsonObj.getJSONArray("m");

                                for(int j=0;j<matchesArray.length();j++) {
                                    JSONObject match = matchesArray.getJSONObject(j);

                                    team1Name = match.getString("t1");
                                    team2Name = match.getString("t2");
                                    team1Flag = match.getString("t1flag");
                                    team2Flag = match.getString("t2flag");
                                    date = match.getString("date");
                                    Long timeStampLong = Long.parseLong(match.getString("t"));
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                    timeStamp = dateFormat.format(new Date(timeStampLong));

                                    JSONObject matchIn3h = match.has("odds")?match.getJSONObject("odds"):null;

                                    if(matchIn3h != null) {
                                        oddsInFavour = matchIn3h.getString("rate");
                                        oddsAgainst = matchIn3h.getString("rate2");
                                        rateTeamName = matchIn3h.getString("rate_team");
                                        key = "2";
                                    }
                                    else {
                                        key = "3";
                                    }
                                    upcomingDataItem.updateItem(key, team1Name, team2Name, team1Flag, team2Flag, date, timeStamp, oddsInFavour, oddsAgainst, rateTeamName);
                                }

                                /*
                                JSONObject childObj = childArr.getJSONObject(0);
                                JSONObject childchildObj= childObj.has("odds")?childObj.getJSONObject("odds"):null;
                                //jsonObj.getString("date")
                                if(childchildObj!=null)
                                    scorecards[i]=new scorecards(childObj.getString("t1"), childObj.getString("t2")
                                            , childchildObj.getString("rate"), childchildObj.getString("rate2"),
                                            childchildObj.getString("rate_team"), childObj.getString("t1flag"), childObj.getString("t2flag"));
                                else
                                    scorecards[i]=new scorecards(childObj.getString("t1"), childObj.getString("t2")
                                            , null, null, null, childObj.getString("t1flag"), childObj.getString("t2flag"));
                                 */
                            }
                            upcomingDataItem.updateItem("4", team1Name, team2Name, team1Flag, team2Flag, date, timeStamp, oddsInFavour, oddsAgainst, rateTeamName);
                            dataList = upcomingDataItem.getDataList();
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
    }

}
