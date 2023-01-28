package course_work;

import java.io.Serializable;

public abstract class Sports_Club implements Serializable {
    private String clubName;
    private String clubLocation;

    public Sports_Club(String clubName, String clubLocation) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
    }
    public Sports_Club(){
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    @Override
    public String toString() {
        return "clubName is = " + clubName + ", clubLocation is = " + clubLocation;
    }

    public abstract String Statics();
}
