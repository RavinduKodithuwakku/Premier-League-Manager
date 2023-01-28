package course_work;


public class School_Football_Clubs extends Football_club{
    private String schName;
    private int schId;


    public School_Football_Clubs(String clubName, String clubLocation, int wins, int draws, int defeats, int goalsScored, int goalsReceived, String schName, int schId){
        super(clubName, clubLocation, wins, draws, defeats, goalsScored, goalsReceived);
        this.schId = schId;
        this.schName = schName;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    public int getSchId() {
        return schId;
    }

    public void setSchId(int schId) {
        this.schId = schId;
    }

    @Override
    public String toString() {
        return "Club Name : " + getClubName() +"\n" + "Club Location : "+ getClubLocation() +"\n" +"School Name : " + schName +"\n"+
                 "School ID : " + schId +"\n"+ "Wins : " + getWins() + "\n" + "Draws : " + getDraws()  +"\n" + "Defeats : " + getDefeats() +
                "\n"+ "Number Of Matches : " + getNoOfMatches()  +"\n"+ "Number of Goals Scored : " + getGoalsScored() +"\n"+
                "Number of Goals Received : " + getGoalsReceived() + "\n" + "Points : " + getPoints() +"\n\n";
    }
}
