package view;

import Controller.AdminController;
import Controller.CinemaController;
import Controller.MovieController;
import Controller.PriceController;

import java.util.Scanner;

public class AdminView {
    public boolean adminMenu(Scanner sc, AdminController adminController, MovieController movieController, CinemaController cinemaController, PriceController priceController) {
        boolean loop = true;
        boolean loggedin = false;
        int choice;
        while(!loggedin){
            System.out.println("Please enter your username and password");
            System.out.println("---------------------------------------");
            System.out.println("Username");
            String username = sc.next();
            System.out.println("Password");
            String password = sc.next();

            if(adminController.authorizeAdmin(username,password)) {
                System.out.println("Login Successful");
                break;
            } else
                System.out.println("Invalid username or password, please try again.");
            System.out.println("Press 1 to continue or 0 to exit.");
            choice = sc.nextInt();
            if (choice == 1)
                continue;
            loop = false;
            break;
        }

        while (loop) {
            printAdminMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1: // create movie
                    movieController.createMovie(sc);
                    break;
                case 2: // update movie
                    movieController.updateMovieDetailsFromInput(sc);
                    break;
                case 3: //remove movie
                    movieController.removeMovieByStatus(sc);
                    break;
                // Cinema Showtimes
                case 4: // create showtime
                    cinemaController.addNewShowtime(sc);
                    break;
                case 5: // update showtime
                    cinemaController.updateShowtime(sc);
                    break;
                case 6: // remove showtime
                    cinemaController.removeShowtime(sc);
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
