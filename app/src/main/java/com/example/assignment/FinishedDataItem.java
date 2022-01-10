package com.example.assignment;

import java.util.ArrayList;

public class FinishedDataItem {

    private static ArrayList<ArrayList<String>> dataList = new ArrayList<>();

    /*
    indexes

    0: key
    1: team1Name
    2: team2Name
    3: team1Flag
    4: team2Flag
    5: score1
    6: score2
    7: overs1
    8: overs2
    9: winner
    10: result
    11: date

     */
    public void updateItem(String key, String team1Name, String team2Name, String team1Flag, String team2Flag, String score1, String score2, String overs1, String overs2, String winner, String result, String date) {
        ArrayList<String> a = new ArrayList<>();
        a.add(key);
        a.add(team1Name);
        a.add(team2Name);
        a.add(team1Flag);
        a.add(team2Flag);
        a.add(score1);
        a.add(score2);
        a.add(overs1);
        a.add(overs2);
        a.add(winner);
        a.add(result);
        a.add(date);


        dataList.add(a);
    }

    ArrayList<ArrayList<String>> getDataList() {
        return dataList;
    }

}
