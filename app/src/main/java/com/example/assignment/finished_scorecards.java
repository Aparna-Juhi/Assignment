package com.example.assignment;

public class finished_scorecards {
    private String match_result, result_details, team_flag_1, team_flag_2,team_name_1, team_name_2;
    private String team1score,team2score,total_overs_1,total_overs_2;
    public finished_scorecards( String match_result, String result_details, String team_flag_1, String team_flag_2,
                               String team_name_1, String team_name_2, String team1score, String team2score, String total_overs_1,
                               String total_overs_2) {
        this.match_result=match_result;

        this.result_details=result_details;
        this.team_flag_1=team_flag_1;
        this.team_flag_2=team_flag_2;
        this.team_name_1=team_name_1;
        this.team_name_2=team_name_2;
        this.team1score=team1score;
        this.team2score=team2score;
        this.total_overs_1=total_overs_1;
        this.total_overs_2=total_overs_2;
    }
    public String getTeam1score() {
        return team1score;
    }
    public String getTeam2score() {
        return team2score;
    }
    public String getTotal_overs_1() {
        return total_overs_1;
    }
    public String getTotal_overs_2() {
        return total_overs_2;
    }
    public String getTeam_flag_2() {
        return team_flag_2;
    }
    public String getTeam_name_1() {
        return team_name_1;
    }
    public String getTeam_name_2() {
        return team_name_2;
    }
    public String getMatch_result() {
        return match_result;
    }
    public String getResult_details() {
        return result_details;
    }
    public String getTeam_flag_1() {
        return team_flag_1;
    }


}
