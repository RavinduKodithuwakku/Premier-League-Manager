package course_work;

import java.io.*;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {

    Scanner input = new Scanner(System.in);
    static ArrayList<Football_club> clubs = new ArrayList<>();
    static ArrayList<DataOfMatch> matchData = new ArrayList<>();

    {
        try {
            FileInputStream fileInputStream = new FileInputStream("save.ser");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            clubs = (ArrayList<Football_club>) inputStream.readObject();
            inputStream.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.print("Save File created");
        }
    }

    {
      try {
          FileInputStream fileInputStreamMatchData = new FileInputStream("History.ser");
          ObjectInputStream inputStreamMatchData = new ObjectInputStream(fileInputStreamMatchData);
          matchData = (ArrayList<DataOfMatch>) inputStreamMatchData.readObject();
          inputStreamMatchData.close();
      }catch (ClassNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          System.out.println("History File created");
      }
    }

    static void serialize(ArrayList<Football_club> clubs) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("save.ser", false);
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(clubs);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void serializeDataOfMatch(ArrayList<DataOfMatch> matchData) {
        try {
            FileOutputStream fileOutputStreamMatchData = new FileOutputStream("History.ser", false);
            ObjectOutputStream outputStreamMatchData = new ObjectOutputStream(fileOutputStreamMatchData);
            outputStreamMatchData.writeObject(matchData);
            outputStreamMatchData.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addingClub() {
                System.out.println("------------- Adding a new club -----------------");
                System.out.println("Your club is an university club : Enter 1");
                System.out.println("Your club is a school club      : Enter 2" + "\n");
                System.out.println("Enter your choice :");

                int choice = input.nextInt();
                input.nextLine();

                System.out.println("Enter name of the club :");
                String nameOfClub = input.next();
                System.out.println("Enter location of the club :");
                String location = input.next();
                System.out.println("Enter the number of wins :");
                int wins = input.nextInt();
                input.nextLine();
                System.out.println("Enter the number of draws :");
                int draws = input.nextInt();
                input.nextLine();
                System.out.println("Enter the number of defeats : ");
                int defeats = input.nextInt();
                input.nextLine();
                System.out.println("Enter the number of goals scored : ");
                int noOfGoals = input.nextInt();
                input.nextLine();
                System.out.println("Enter the number of goals received : ");
                int noOfReceived = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter the university name :");
                        String uniName = input.nextLine();
                        System.out.println("Enter the university ID : ");
                        int uniID = input.nextInt();
                        input.nextLine();
                        University_Football_Club football_club = new University_Football_Club(nameOfClub, location, wins, draws, defeats,
                                noOfGoals, noOfReceived, uniName, uniID);
                        clubs.add(football_club);
                        break;


                    case 2:
                        System.out.println("Enter School Name :");
                        String schName = input.nextLine();
                        System.out.println("Enter school ID : ");
                        int schID = input.nextInt();
                        input.nextLine();
                        School_Football_Clubs school_football_clubs = new School_Football_Clubs(nameOfClub, location, wins, draws, defeats,
                                noOfGoals, noOfReceived, schName, schID);
                        clubs.add(school_football_clubs);
                        break;

                    default:
                        System.out.println("Invalid input, Please enter a valid input.");
                }
                serialize(clubs);
        }

    @Override
    public void deleteClub(){
        System.out.println("---------Deleting a club----------");
        System.out.println("Enter name of the club : ");
        String delClub = input.next();

        for (Sports_Club club : clubs){
            if (club.getClubName().equals(delClub)){
                clubs.remove(club);
                System.out.println("The club " + delClub + " is deleted.");
                serialize(clubs);
                return;
            }else {
                System.out.println("No club found in the system");
            }
        }
    }

    @Override
    public void displayStatics() {

        if (clubs.size() == 0){
            System.out.println("Your sports clubs not in this system.");
            return;
        }
        System.out.println("-----------Display Statics---------");
        System.out.println("Enter name of the club to display statics :");
        String clubStats = input.nextLine();

        for (Football_club club : clubs) {

            if (club.getClubName().equals(clubStats)){
                System.out.println(club.Statics());
                return;
            }
        }

    }

    @Override
    public void displayTable() {
        Comparator<Football_club> sorting = Comparator.comparing(Football_club::getPoints).thenComparing(Football_club::getGoalDifference);
        clubs.sort(sorting);
        Collections.reverse(clubs);

            System.out.println("------------------------------------Display the Premier League Table-----------------------------------------------"+ "\n");
            System.out.println("******************************************************************************************************************");
            System.out.printf("%-18s %-9s %-8s %-8s %-10s %-20s %-15s %-17s " , "Club_Name" , "Points","Wins","Draws","Defeats","Number_Of_Matches",
                    "Goals_Scored","Goals_Received");
            System.out.println();
            System.out.println("******************************************************************************************************************");
            for(Football_club club : clubs ){
                System.out.format("%-18s %-9s %-8s %-8s %-10s %-20s %-15s %-17s ", club.getClubName(),club.getPoints(),club.getWins(),
                        club.getDraws(),club.getDefeats(),club.getNoOfMatches(),club.getGoalsScored(),club.getGoalsReceived());
                System.out.println();

            }
            System.out.println("*****************************************************************************************************************"+ "\n\n");
        }


    @Override
    public void addMatch() {
        System.out.println("---------------------Add Match---------------------" + "\n");

        if (clubs.size() <= 1) {
            System.out.println("Not enough teams to add a match");
        } else {
            int i = 1;

            for (Sports_Club club : clubs) {
                System.out.println(i + ". " + club.getClubName());
                i++;
            }
            System.out.println("\nSelect club 1");
            int club01 = input.nextInt() - 1;
            input.nextLine();

            System.out.println("Enter number of goals scored by " + clubs.get(club01).getClubName());
            int scoredGoalsClub01 = input.nextInt();
            input.nextLine();
            clubs.get(club01).setGoalsScored(clubs.get(club01).getGoalsScored() + scoredGoalsClub01);

            System.out.println("\nselect club 2");
            int club02 = input.nextInt() - 1;
            input.nextLine();

            System.out.println("Enter number of goals scored by " + clubs.get(club02).getClubName());
            int scoredGoalsClub02 = input.nextInt();
            input.nextLine();
            clubs.get(club02).setGoalsScored(clubs.get(club02).getGoalsScored() + scoredGoalsClub02);

            System.out.println("Enter played day :");
            int day = input.nextInt();
            input.nextLine();

            System.out.println("Enter played month :");
            int month = input.nextInt();
            input.nextLine();

            System.out.println("Enter played year :");
            int year = input.nextInt();
            input.nextLine();
            Date date =new Date(day,month,year);

            clubs.get(club01).setGoalsReceived(clubs.get(club01).getGoalsReceived() + scoredGoalsClub01);
            clubs.get(club02).setGoalsReceived(clubs.get(club02).getGoalsReceived() + scoredGoalsClub02);

            if (scoredGoalsClub01 > scoredGoalsClub02) {
                clubs.get(club01).setWins(clubs.get(club01).getWins() + 1);
                clubs.get(club02).setDefeats(clubs.get(club02).getDefeats() + 1);

            } else if (scoredGoalsClub01 == scoredGoalsClub02) {
                clubs.get(club01).setDraws(clubs.get(club01).getDraws() + 1);
                clubs.get(club02).setDraws(clubs.get(club02).getDraws() + 1);

            } else {
                clubs.get(club01).setDefeats(clubs.get(club01).getDefeats() + 1);
                clubs.get(club02).setWins(clubs.get(club02).getWins() + 1);
            }

            DataOfMatch dataOfMatch = new DataOfMatch(clubs.get(club01).getClubName(),clubs.get(club02).getClubName(),scoredGoalsClub01,scoredGoalsClub02,date);
            matchData.add(dataOfMatch);

            serialize(clubs);
            serializeDataOfMatch(matchData);

        }
    }

    @Override
    public  void randomMatch(){
        if (clubs.size() <= 1 ){
            System.out.println("Not enough teams.");
        }else {
            Random random = new Random();
            int clubOne = random.nextInt(clubs.size());
            int clubTwo;
            do{
                clubTwo = random.nextInt(clubs.size());
            }while (clubOne == clubTwo);

            int scoredGoalsClubOne = random.nextInt(10);
            clubs.get(clubOne).setGoalsScored(clubs.get(clubOne).getGoalsScored() + scoredGoalsClubOne);

            int scoredGoalsClubTwo = random.nextInt(10);
            clubs.get(clubTwo).setGoalsScored(clubs.get(clubTwo).getGoalsScored() + scoredGoalsClubTwo);


            clubs.get(clubOne).setGoalsReceived(clubs.get(clubOne).getGoalsReceived() + scoredGoalsClubTwo);
            clubs.get(clubTwo).setGoalsReceived(clubs.get(clubTwo).getGoalsReceived() + scoredGoalsClubOne);


            if (scoredGoalsClubOne > scoredGoalsClubTwo) {
                clubs.get(clubOne).setWins(clubs.get(clubOne).getWins() + 1);
                clubs.get(clubTwo).setDefeats(clubs.get(clubTwo).getDefeats() + 1);

            } else if (scoredGoalsClubOne == scoredGoalsClubTwo) {
                clubs.get(clubOne).setDraws(clubs.get(clubOne).getDraws() + 1);
                clubs.get(clubTwo).setDraws(clubs.get(clubTwo).getDraws() + 1);

            } else {
                clubs.get(clubOne).setDefeats(clubs.get(clubOne).getDefeats() + 1);
                clubs.get(clubTwo).setWins(clubs.get(clubTwo).getWins() + 1);
            }

            int day = random.nextInt(30)+1;
            int month = random.nextInt(11) + 1;
            int year = random.nextInt(10) + 2020;
            Date date = new Date(day, month, year);

            DataOfMatch dataOfMatch = new DataOfMatch(clubs.get(clubOne).getClubName(),clubs.get(clubTwo).getClubName(),scoredGoalsClubOne,scoredGoalsClubTwo,date);
            matchData.add(dataOfMatch);

            serialize(clubs);
            serializeDataOfMatch(matchData);

        }
    }
}