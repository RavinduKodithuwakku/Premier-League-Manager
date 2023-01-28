package course_work;

public class Football_club extends Sports_Club {

    private int wins;
    private int draws;
    private int defeats;
    private int goalsScored;
    private int goalsReceived;
    private int noOfMatches = wins + draws + defeats;
    private int points = (wins * 3) + draws;
    private int goalDifference = goalsScored - goalsReceived;


    public Football_club(String clubName, String clubLocation, int wins, int draws, int defeats,
                          int goalsScored, int goalsReceived) {
        super(clubName,clubLocation);
        this.wins = wins;
        this.draws = draws;
        this.defeats = defeats;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
    }

    public int getPoints() {
        return (getWins() * 3) +getDraws();
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) { this.wins = wins; }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getNoOfMatches() {
        return (getWins() + getDraws() + getDefeats());
    }

    public void setNoOfMatches(int noOfMatches) {
        this.noOfMatches = noOfMatches;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    @Override
    public String Statics() {
        return "Wins : " + wins  + "\n" + "Draws : " + draws  +"\n" + "Defeats : " + defeats +
                "\n"+ "Number of Goals Scored : " + goalsScored +"\n"+ "Number of Goals Received : " + goalsReceived + "\n" +
                 "Number Of Matches : " + getNoOfMatches() +"\n" +"Points : " + getPoints() +"\n\n";

    }
}
