package course_work;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();

        while (true) {
            try {
                System.out.println("--------------------------------------------");
                System.out.println("            Premier League Manager          ");
                System.out.println("--------------------------------------------" + "\n");
                System.out.println("Add a new club                    : Enter 1");
                System.out.println("Delete a club                     : Enter 2");
                System.out.println("Display statics of a club         : Enter 3");
                System.out.println("Display the premier league table  : Enter 4");
                System.out.println("Add a played match                : Enter 5");
                System.out.println("Display the GUI                   : Enter 6");
                System.out.println("To Exit                           : Enter 7" + "\n");
                System.out.println("Enter your choice : ");

                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        premierLeagueManager.addingClub();
                        break;
                    case 2:
                        premierLeagueManager.deleteClub();
                        break;
                    case 3:
                        premierLeagueManager.displayStatics();
                        break;
                    case 4:
                        premierLeagueManager.displayTable();
                        break;
                    case 5:
                        premierLeagueManager.addMatch();
                        break;
                    case 6:
                        GUI.main(args);
                        break;
                    case 7:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid input, Please enter a valid input ");
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input, Please enter a valid Integer. " + "\n");
                sc.nextLine();
            }
        }
    }
}
