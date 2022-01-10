package com.example.assignment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class finished_frag extends Fragment {

    Context mContext;
    static final String key = "data_transfer_key_for_bundling";
    ArrayList<ArrayList<String>> dataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.finished_frag, container, false);
        RecyclerView finRecViu = v.findViewById(R.id.recycler_view_for_finished_matches);

        Log.d("test", "on create ok");
        finRecViu.setLayoutManager(new LinearLayoutManager(getMyContext()));
        //ArrayList<String> dataList = getArguments().getStringArrayList(key);
        AdapterForFinishedMatches adapter = new AdapterForFinishedMatches(dataList);
        finRecViu.setAdapter(adapter);
        return v;
    }

    private Context getMyContext() {
        if(mContext == null)
            return getContext();
        return mContext;
    }

    public static finished_frag newInstance(Object data) {

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


        new finished_frag().getData();
        return f;
    }

    private void getData() {
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

                                /*
                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                scorecards[i]=new scorecards(jsonObj.getString("date"), "Delhi");
                                */

                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                date = jsonObj.getString("date");

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
                                    result = match.getString("result");

                                    JSONObject matchIn3h = match.has("odds")?match.getJSONObject("odds"):null;

                                    finishedDataItem.updateItem("2", team1Name, team2Name, team1Flag, team2Flag, score1, score2, overs1, overs2, winner, result, date);
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
                            finishedDataItem.updateItem("2", team1Name, team2Name, team1Flag, team2Flag, score1, score2, overs1, overs2, winner, result, date);
                            dataList = finishedDataItem.getDataList();
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

