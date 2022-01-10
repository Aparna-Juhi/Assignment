package com.example.assignment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UpcomingDataItem {

    private static ArrayList<ArrayList<String>> dataList = new ArrayList<>();

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

}
