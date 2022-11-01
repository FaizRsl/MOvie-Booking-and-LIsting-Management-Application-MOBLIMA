import Controller.DatabaseController;
import Model.Cinema.Cinema;
import Model.Movie.Movie;
import Model.Ticket.Ticket;
import Model.User.Admin;
import Model.User.SerializeDB;
import Model.User.StaffLogin;
import Model.User.User;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MOBLIMA {

    private static DatabaseManager dbM = new DatabaseManager();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean loop = true;
        do {
            printMenu();
            int choice = Integer.parseInt(br.readLine());
            switch (choice) {
                case 1:
                    loop = adminMenu(br);
                    break;
                case 2:
                    loop = customerMenu(br);
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    System.out.println("Goodbye!");
                    loop = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice!");
                    break;
            }
        } while(loop);
        br.close();
    }

    private static void printMenu() {
        System.out.println("----------------Main Menu-----------------");
        System.out.println("------------------------------------------");
        System.out.println("1. Admin");
        System.out.println("2. Movie Goers");
        System.out.println("3. Exit");
        System.out.println("------------------------------------------");
        System.out.println("Please enter the choice for type of user:");
    }

    private static boolean adminMenu(BufferedReader br) throws IOException {
        boolean loop = true;
        boolean loggedin = false;
        while(!loggedin){
            System.out.println("Please enter your username and password");
            System.out.println("---------------------------------------");
            System.out.println("Username");
            String username = br.readLine();
            System.out.println("Password");
            String password = br.readLine();

            if(dbM.authorizeAdmin(username,password)) {
                System.out.println("Login Successful");
                loggedin = true;
            } else {
                System.out.println("Invalid username or password, please try again.");
            }
        }

        //Movie movie = new Movie();

        while (loop) {
            printAdminMenu();
            int choice = Integer.parseInt(br.readLine());
            switch (choice) {
                case 1:
                    dbM.addMovieInput();
                    break;
                case 2:
                    dbM.viewAllMovies();
                    break;
                case 3:
                    Admin admin = new Admin("admin","password");
                    dbM.addAdmin(admin);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("Sure, returning back to main menu.");
                    loop = false;
                    break;
                case 9:
                    System.out.println("Exiting the program...");
                    System.out.println("Goodbye!");
                    return false;
                default:
                    System.out.println("Please enter a valid choice!");
                    break;
            }
        }
        return true;
    }

    private static boolean customerMenu(BufferedReader br) throws IOException {
        boolean loop = true;
        int choice;
    do {
            printCustomerMenu();
            choice = Integer.parseInt(br.readLine());
            switch(choice) {
                case 1:
                    printListMovieMenu();
                    choice = Integer.parseInt(br.readLine());
                    if(choice == 9)
                        break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Sure, returning back to main menu.");
                    loop = false;
                    break;
                case 5:
                    System.out.println("Exiting the program...");
                    System.out.println("Goodbye!");
                    return false;
                default:
                    System.out.println("Please enter a valid choice!");
                    break;
            }
        } while (loop);
        return true;
    }

    private static void printCinemaList() {
        System.out.println("---------------- Model.Cinema.Cinema List -----------------");
        System.out.println("----------------------------------------------");
        System.out.println("1. Shaw");
        System.out.println("2. Apple");
        System.out.println("3. Potato");
    }

    private static void printAdminMenu() {
        System.out.println("----------------Admin Menu -----------------");
        System.out.println("--------------------------------------------");
        System.out.println("1. Create Model.Movie.Movie List");
        System.out.println("2. Update Model.Movie.Movie List");
        System.out.println("3. Remove Model.Movie.Movie List");
        System.out.println("4. Create Model.Cinema.Cinema Show times and Movies");
        System.out.println("5. Update Model.Cinema.Cinema Show times and Movies");
        System.out.println("6. Remove Model.Cinema.Cinema Show times and Movies");
        System.out.println("7. Configure System Settings");
        System.out.println("8. Go back to Main Menu");
        System.out.println("9. Exit Menu");
        System.out.println("--------------------------------------------");
        System.out.println("Please enter your choice:");

    }

    private static void printCustomerMenu() {
        System.out.println("----------------Customer Menu -----------------");
        System.out.println("-----------------------------------------------");
        System.out.println("1. List Movies");
        System.out.println("2. Book and purchase ticket");
        System.out.println("3. View booking history");
        System.out.println("4. Go back to Main Menu");
        System.out.println("5. Exit Menu");
        System.out.println("-----------------------------------------------");
        System.out.println("Please enter your choice:");
    }

    private static void printListMovieMenu(){
        System.out.println("----------------Customer Menu -----------------");
        System.out.println("-----------------------------------------------");
        System.out.println("1. Search Movie");
        System.out.println("2. List Current Showing Movie");
        System.out.println("3. List Preview Movie");
        System.out.println("4. List Coming Soon Movie");
        System.out.println("5. List Ended Movie");
        System.out.println("6. List All Movie");
        System.out.println("7. List Top 5 movie by overall reviewers' rating");
        System.out.println("8. List Top 5 movie by ticket sales");
        System.out.println("9. Go back to Main Menu");
        System.out.println("10. Exit Menu");
    }
}

