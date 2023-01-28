package course_work;

public class University_Football_Club extends Football_club {
    private String uniName;
    private int uniId;

    public University_Football_Club(String clubName, String clubLocation, int wins, int draws, int defeats, int goalsScored, int goalsReceived, String uniName, int uniId){
        super(clubName, clubLocation, wins, draws, defeats, goalsScored, goalsReceived);
        this.uniId = uniId;
        this.uniName = uniName;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public int getUniId() {
        return uniId;
    }

    public void setUniId(int uniId) {
        this.uniId = uniId;
    }

    @Override
    public String toString() {
        return"Club Name : " + getClubName() +"\n" +"Club Location : "+ getClubLocation() +"\n"+"University Name :  " + uniName +"\n"+ "University ID : " + uniId +"\n" +
                "Wins : " + getWins() + "\n" + "Draws : " + getDraws()  +"\n" + "Defeats : " + getDefeats() +
                "\n"+ "Number Of Matches : " + getNoOfMatches()  +"\n"+ "Number of Goals Scored : " + getGoalsScored() +"\n"+
                "Number of Goals Received : " + getGoalsReceived() + "\n" + "Points : " +getPoints() +"\n\n";
    }
}
