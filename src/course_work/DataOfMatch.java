package course_work;

import java.io.Serializable;

public class DataOfMatch implements Serializable {
    String clubOne;
    String clubTwo;
    int clubOneGoals;
    int clubTwoGoals;
    Date date;

    public DataOfMatch(String clubOne, String clubTwo, int clubOneGoals, int clubTwoGoals, Date date) {
        this.clubOne = clubOne;
        this.clubTwo = clubTwo;
        this.clubOneGoals = clubOneGoals;
        this.clubTwoGoals = clubTwoGoals;
        this.date = date;
    }

    public String getClubOne() {
        return clubOne;
    }

    public void setClubOne(String clubOne) {
        this.clubOne = clubOne;
    }

    public String getClubTwo() {
        return clubTwo;
    }

    public void setClubTwo(String clubTwo) {
        this.clubTwo = clubTwo;
    }

    public int getClubOneGoals() {
        return clubOneGoals;
    }

    public void setClubOneGoals(int clubOneGoals) {
        this.clubOneGoals = clubOneGoals;
    }

    public int getClubTwoGoals() {
        return clubTwoGoals;
    }

    public void setClubTwoGoals(int clubTwoGoals) {
        this.clubTwoGoals = clubTwoGoals;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
