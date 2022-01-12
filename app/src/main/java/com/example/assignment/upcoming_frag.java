
package com.example.assignment;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class upcoming_frag extends Fragment {
    private Context mContext;
    private Context getMyContext(){
        if(mContext == null)
            mContext = getContext();
        return mContext;
    }

    scorecards[] scorecards;
    mylistadapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.upcoming_frag, container, false);

        RequestQueue queue = Volley.newRequestQueue(getMyContext());
        String url ="https://mocki.io/v1/30786c0a-390e-41d5-9ad8-549ed26cba64";

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.upcoming_recyclerview);
        scorecards = new scorecards[0];
        adapter = new mylistadapter(scorecards);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext()));
        recyclerView.setAdapter(adapter);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArr = new JSONArray(response);
                            scorecards = new scorecards[jsonArr.length()+1];

                            for (int i = 0; i < jsonArr.length(); i++)
                            {


                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                JSONArray childArr= jsonObj.getJSONArray("m");
                                JSONObject childObj = childArr.getJSONObject(0); ;


                                JSONObject childchildObj= childObj.has("odds")?childObj.getJSONObject("odds"):null;


                                if(childchildObj!=null){
                                    scorecards[i]=new scorecards(1,childObj.getString("t1"), childObj.getString("t2")
                                            , childchildObj.getString("rate"),childchildObj.getString("rate2"),
                                            childchildObj.getString("rate_team"), childObj.getString("t1flag"),
                                            childObj.getString("t2flag"),childObj.getString("t"), childObj.getString("t"));}
                                else
                                    scorecards[i]=new scorecards(1, childObj.getString("t1"), childObj.getString("t2")
                                            , null,null, null, childObj.getString("t1flag"),childObj.getString("t2flag"),childObj.getString("t"),
                                            childObj.getString("t"));
                            }
                            scorecards[jsonArr.length()]=new scorecards(2, null, null,null, null, null,null, null,null, null);
                            // adapter.scorecards = scorecards.getDataList();





                        } catch (JSONException e) {
                            Log.e("error message", ""+e.getMessage());
                            e.printStackTrace();
                        }

                        adapter.scorecards = scorecards;
                        adapter.notifyDataSetChanged();
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
    public static upcoming_frag newInstance() {

        upcoming_frag f = new upcoming_frag();
        return f;
    }
}
/*package com.example.assignment;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class upcoming_frag extends Fragment {
    private Context mContext;
    private Context getMyContext(){
        if(mContext == null)
            mContext = getContext();
        return mContext;
    }

    scorecards[] scorecards;
    mylistadapter adapter;
    long t;int i;long difference_in_timestamps;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.upcoming_frag, container, false);

        RequestQueue queue = Volley.newRequestQueue(getMyContext());
        String url ="https://mocki.io/v1/30786c0a-390e-41d5-9ad8-549ed26cba64";

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.upcoming_recyclerview);
        scorecards = new scorecards[0];
        adapter = new mylistadapter(scorecards);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext()));
        recyclerView.setAdapter(adapter);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArr = new JSONArray(response);
                            scorecards = new scorecards[jsonArr.length()+1];

                            for (i = 0; i < jsonArr.length(); i++)
                            {


                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                JSONArray childArr= jsonObj.getJSONArray("m");
                                JSONObject childObj = childArr.getJSONObject(0);
                                long time_of_match=Long.parseLong(childObj.getString("t")) ;
                                //long time_of_match=System.currentTimeMillis() + 10200000;

                                SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm aa");
                                SimpleDateFormat formatDate=new SimpleDateFormat("d MMM ");
                                long system_time=System.currentTimeMillis();
                                JSONObject childchildObj= childObj.has("odds")?childObj.getJSONObject("odds"):null;
                                difference_in_timestamps=0;

                                if((time_of_match - system_time) <= 10800000) {
                                    difference_in_timestamps = time_of_match -system_time;


                                    t= difference_in_timestamps;

                                    new CountDownTimer(difference_in_timestamps, 1000)
                                    {
                                        @Override
                                        public void onTick(long l) {
                                            t--;
                                            String time = formatTime.format(
                                                    difference_in_timestamps);


                                            if(childchildObj!=null) {
                                                try {
                                                    scorecards[i]=new scorecards(1, childObj.getString("t1"), childObj.getString("t2")
                                                            , childchildObj.getString("rate"),childchildObj.getString("rate2"),
                                                            childchildObj.getString("rate_team"), childObj.getString("t1flag"),childObj.getString("t2flag"),time, "Starting In :");
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            else {
                                                try {
                                                    scorecards[i]=new scorecards(1, childObj.getString("t1"), childObj.getString("t2")
                                                            , null,null, null, childObj.getString("t1flag"),childObj.getString("t2flag"), time, "Starting In :");
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                        }


                                        @Override
                                        public void onFinish() {
                                            Log.d("abc", "xyz");

                                        }
                                    }.start();



                                }

                                else
                                {

                                    String time = formatTime.format(
                                            time_of_match);
                                    String date=formatDate.format(time_of_match);
                                    scorecards[i]=new scorecards(1, childObj.getString("t1"), childObj.getString("t2")
                                            , childchildObj.getString("rate"),childchildObj.getString("rate2"),
                                            childchildObj.getString("rate_team"), childObj.getString("t1flag"),
                                            childObj.getString("t2flag"),date, time);

                                }


/*

                                if(childchildObj!=null){
                                scorecards[i]=new scorecards(childObj.getString("t1"), childObj.getString("t2")
                                , childchildObj.getString("rate"),childchildObj.getString("rate2"),
                                        childchildObj.getString("rate_team"), childObj.getString("t1flag"),
                                        childObj.getString("t2flag"),date, time);}
                                else
                                    scorecards[i]=new scorecards(childObj.getString("t1"), childObj.getString("t2")
                                            , null,null, null, childObj.getString("t1flag"),childObj.getString("t2flag"), date, time);
                            }

 */
 /*
                            scorecards[jsonArr.length()]=new scorecards(2, null, null,null, null, null,null, null,null, null);

                        } catch (JSONException e) {
                            Log.e("error message", ""+e.getMessage());
                            e.printStackTrace();
                        }

                        adapter.scorecards = scorecards;
                        adapter.notifyDataSetChanged();
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
    public static upcoming_frag newInstance() {

        upcoming_frag f = new upcoming_frag();
        return f;
    }
}
*/