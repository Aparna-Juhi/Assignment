package com.example.assignment;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Handler;

public class UpcomingDataItem {

    private ArrayList<ArrayList<String>> dataList;

    public UpcomingDataItem() {
        dataList = new ArrayList<>();
    }

    /*
    indexes

    0: key
    1: team1Name
    2: team2Name
    3: team1Flag
    4: team2Flag
    5: date
    6: timeStamp
    7: oddsInFavour
    8: oddsAgainst
    9: rateTeamName

     */
    public void updateItem(String key, String team1Name, String team2Name, String team1Flag, String team2Flag, String date, String timeStamp, String oddsInFavour, String oddsAgainst, String rateTeamName) {
        ArrayList<String> a = new ArrayList<>();
        a.add(key);
        a.add(team1Name);
        a.add(team2Name);
        a.add(team1Flag);
        a.add(team2Flag);
        a.add(date);
        a.add(timeStamp);
        a.add(oddsInFavour);
        a.add(oddsAgainst);
        a.add(rateTeamName);

        dataList.add(a);
    }

    ArrayList<ArrayList<String>> getDataList() {
        return dataList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    ArrayList<ArrayList<String>> getUpdatedDataList() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String curTime = now.format(dtf);
        for(int i=0; i<dataList.size();i++) {
            Log.d("test", "getUpdateDataList");
            ArrayList<String> a = dataList.get(i);
            String timeStamp = a.get(6);

            if(timeStamp.equals(""))
                continue;

            int h = 0;
            int m = 0;
            try {
                h = Integer.parseInt(timeStamp.substring(0, timeStamp.indexOf('h'))) - Integer.parseInt(curTime.substring(0, curTime.indexOf(':')));
                m = Integer.parseInt(timeStamp.substring(timeStamp.indexOf(':') + 1, timeStamp.indexOf('m'))) - Integer.parseInt(curTime.substring(curTime.indexOf(':') + 1));
                Log.d("time", curTime + " " + timeStamp);
                m--;
                if (m < 0) {
                    h--;
                    m = 59;
                }
            }
            catch (Exception e) {
                Log.d("test", e.toString());
            }
            timeStamp = h +"h:" + m +"m";
            Log.d("test", timeStamp);
            a.set(6, timeStamp);
        }

        return dataList;
    }

}
