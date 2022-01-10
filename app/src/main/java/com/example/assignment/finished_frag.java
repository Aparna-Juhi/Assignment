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

public class finished_frag extends Fragment {
    private Context mContext;
    private Context getMyContext(){
        if(mContext == null)
            mContext = getContext();
        return mContext;
    }

    finished_scorecards[] finished_scorecards;
    finished_adapter finished_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.finished_frag, container, false);


        RequestQueue queue = Volley.newRequestQueue(getMyContext());
        String url ="https://mocki.io/v1/2389d44c-81aa-4e04-bd2e-b8c7e17572c0";

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.finished_recyclerview);
        finished_scorecards = new finished_scorecards[0];
        finished_adapter = new finished_adapter(finished_scorecards);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext()));
        recyclerView.setAdapter(finished_adapter);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArr = new JSONArray(response);
                            finished_scorecards = new finished_scorecards[jsonArr.length()];

                            for (int i = 0; i < jsonArr.length(); i++)
                            {
                                /*
                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                scorecards[i]=new scorecards(jsonObj.getString("date"), "Delhi");
                                    */

                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                JSONArray childArr= jsonObj.getJSONArray("m");
                                JSONObject childObj = childArr.getJSONObject(0);
//jsonObj.getString("date"),
                                if(childObj!=null)
                                    finished_scorecards[i]=new finished_scorecards(childObj.getString("winner"), childObj.getString("result")
                                            , childObj.getString("t1flag"),childObj.getString("t2flag"),
                                            childObj.getString("t1"), childObj.getString("t2"),childObj.getString("score1"),
                                            childObj.getString("score2") , childObj.getString("overs1") ,
                                            childObj.getString("overs2") );
                                else
                                    finished_scorecards[i]=new finished_scorecards(null,null
                                            , null,null, null,null,
                                            null,null, null, null);
                            }

                        } catch (JSONException e) {
                            Log.e("error message", ""+e.getMessage());
                            e.printStackTrace();
                        }

                        finished_adapter.finished_scorecards = finished_scorecards;
                        finished_adapter.notifyDataSetChanged();

                        // textView.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
            }
        });;

        queue.add(stringRequest);

        return v;
    }
    public static finished_frag newInstance() {

        finished_frag f = new finished_frag();
        return f;
    }
}

