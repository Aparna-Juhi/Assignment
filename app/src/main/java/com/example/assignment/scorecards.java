package com.example.assignment;
public class scorecards {
    private String t1,t2,t1flag,t2flag,rate,rate2,rate_team, time, starting_in;
    public scorecards(String t1, String t2, String rate, String rate2, String rate_team, String t1flag, String t2flag, String time, String starting_in) {
        this.t1 = t1;
        this.t2 = t2;
        this.rate = rate;
        this.rate2 = rate2;
        this.rate_team=rate_team;
        this.t1flag=t1flag;
        this.t2flag=t2flag;
        this.starting_in=starting_in;
        this.time=time;
    }
    public String gett1() {
        return t1;
    }
    public String gett2() {
        return t2;
    }
    public String gett1flag() {
        return t1flag;
    }
    public String gett2flag() {
        return t2flag;
    }
    public String getrate() {
        return rate;
    }
    public String getrate2() {
        return rate2;
    }
    public String getrate_team() {
        return rate_team;
    }

    public String getStarting_in() {
        return starting_in;
    }
    public String getTime()
    {
        return time;
    }
}

