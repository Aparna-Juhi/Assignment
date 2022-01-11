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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class upcoming_frag extends Fragment {

    Context mContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.upcoming_frag, container, false);
        RecyclerView upcRecViu = v.findViewById(R.id.recycler_view_for_upcoming_matches);
        upcRecViu.setLayoutManager(new LinearLayoutManager(getMyContext()));
        Log.d("check", "onCreateRunning");
        //ArrayList<ArrayList<String>> dataList= getArguments().getStringArrayList("transfer_to_new_fragment");

        ArrayList<ArrayList<String>> dataList = new ArrayList<>();
        AdapterForUpcomingMatches adapter = new AdapterForUpcomingMatches(dataList);

        getData(adapter);
        //upcRecViu.setHasFixedSize(true);
        upcRecViu.setAdapter(adapter);

        return v;
    }

    private Context getMyContext(){
        if(mContext == null)
            mContext = getContext();
        return mContext;
    }

    public upcoming_frag newInstance(Object data) {

        upcoming_frag f = new upcoming_frag();

        //Bundle args = new Bundle();

        //f.getData();

        //args.putStringArrayList("transfer_to_new_fragment", new upcoming_frag().dataList);
        //f.setArguments(args);

        return f;
    }


     void getData(AdapterForUpcomingMatches adapter) {
        RequestQueue queue = Volley.newRequestQueue(getMyContext());
        String url ="https://mocki.io/v1/30786c0a-390e-41d5-9ad8-549ed26cba64";
        Log.d("test", "getData Loaded");


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
                                date = (jsonObj.getString("date"));
                                // Formatted date
                                SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
                                Date dt1 = null;
                                try {
                                    dt1 = format1.parse(date);
                                }
                                catch (Exception e) {
                                    Log.d("error", "incorrect parsig of date");
                                }
                                DateFormat format2=new SimpleDateFormat("EEEE");
                                String dayOfWeek=format2.format(dt1);
                                String month[] = {" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                                date = dayOfWeek +", "+ Integer.parseInt(date.substring(date.indexOf('/')+1, date.lastIndexOf('/'))) +" "+ month[Integer.parseInt(date.substring(0, date.indexOf('/')))];

                                upcomingDataItem.updateItem("1", team1Name, team2Name, team1Flag, team2Flag, date, timeStamp, oddsInFavour, oddsAgainst, rateTeamName);

                                JSONArray matchesArray= jsonObj.getJSONArray("m");

                                for(int j=0;j<matchesArray.length();j++) {
                                    JSONObject match = matchesArray.getJSONObject(j);

                                    team1Name = match.getString("t1");
                                    team2Name = match.getString("t2");
                                    team1Flag = match.getString("t1flag");
                                    team2Flag = match.getString("t2flag");
                                    date = match.getString("date");
                                    date = Integer.parseInt(date.substring(date.indexOf('/')+1, date.lastIndexOf('/'))) +" "+ month[Integer.parseInt(date.substring(0, date.indexOf('/')))];

                                    Long timeStampLong = Long.parseLong(match.getString("t"));
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
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

                            adapter.dataList = upcomingDataItem.getDataList();
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
    }

    String getFormarttedDate(String date)  {
        //String input_date="01/08/2012";
        /*
        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        Date dt1=null;
        try {
            dt1 = format1.parse(date);
        }
        catch (Exception e) {}
        DateFormat format2=new SimpleDateFormat("EEEE");
        String finalDay=format2.format(dt1);
        String res=finalDay+" ";
        */
        String res="";
        int day = Integer.parseInt(date.substring(date.indexOf('/')));
        int month = Integer.parseInt(date.substring(date.indexOf('/')+1, date.lastIndexOf('/')));
        res += day+" ";

        switch(month) {
            case 1:
                res+="January";  break;
            case 2:
                res+="February";  break;
            case 3:
                res+="March";  break;
            case 4:
                res+="April";  break;
            case 5:
                res+="May";  break;
            case 6:
                res+="Jun";  break;
            case 7:
                res+="July";  break;
            case 8:
                res+="August";  break;
            case 9:
                res+="September";  break;
            case 10:
                res+="October";  break;
            case 11:
                res+="November";  break;
            case 12:
                res+="December";  break;
        }
        return res;
    }

}
