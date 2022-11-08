package view;

import Controller.AdminController;
import Controller.CinemaController;
import Controller.MovieController;
import Controller.PriceController;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class AdminView {
    public static boolean adminMenu(BufferedReader br, AdminController adminController, MovieController movieController, CinemaController cinemaController, PriceController priceController) throws IOException {
        boolean loop = true;
        boolean loggedin = false;
        int choice;
        System.out.println(new File("").getAbsolutePath());
        while(!loggedin){
            System.out.println("Please enter your username and password");
            System.out.println("---------------------------------------");
            System.out.println("Username");
            String username = br.readLine();
            System.out.println("Password");
            String password = br.readLine();

            if(adminController.authorizeAdmin(username,password)) {
                System.out.println("Login Successful");
                loggedin = true;
            } else {
                System.out.println("Invalid username or password, please try again.");
            }
            System.out.println("Press 1 to continue or 0 to exit.");
            choice = Integer.parseInt(br.readLine());
            if(choice == 1){
                continue;
            }
            else
                break;
        }

        while (loop) {
            printAdminMenu();
            choice = Integer.parseInt(br.readLine());
            switch (choice) {
                case 1: // create movie
                    movieController.createMovie(br);
                    break;
                case 2: // update movie
                    movieController.updateMovieDetails();
                    break;
                case 3: //remove movie
                    movieController.removeMovieByStatus();
                    break;
                // Cinema Showtimes
                case 4: // create showtime
                    cinemaController.addNewShowtime();
                    break;
                case 5: // update showtime
                    cinemaController.updateShowtime();
                    break;
                case 6: // remove showtime
                    cinemaController.removeShowtime();
                    break;
                case 7: //Configure System Settings -> price config settings
                    priceController.setPrices();
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
    private static void printAdminMenu() {
        System.out.println("----------------Admin Menu -----------------");
        System.out.println("--------------------------------------------");
        System.out.println("1. Create Movie List");
        System.out.println("2. Update Movie List");
        System.out.println("3. Remove Movie List");
        System.out.println("4. Create Cinema Show times and Movies");
        System.out.println("5. Update Cinema Show times and Movies");
        System.out.println("6. Remove Cinema Show times and Movies");
        System.out.println("7. Configure System Settings");
        System.out.println("8. Go back to Main Menu");
        System.out.println("9. Exit Menu");
        System.out.println("--------------------------------------------");
        System.out.println("Please enter your choice:");

    }

    
    
}
