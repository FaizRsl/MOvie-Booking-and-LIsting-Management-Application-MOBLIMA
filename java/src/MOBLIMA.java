import Controller.AdminController;
import Controller.MovieController;
import Model.Movie.Movie;
import Model.Seat.SeatLayout;
import Model.User.Admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MOBLIMA {

    private static MovieController movieController = new MovieController();
    private static AdminController adminController = new AdminController();

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

            if(adminController.authorizeAdmin(username,password)) {
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
                    movieController.createMovie(br);
                    break;
                case 2:
                    break;
                case 3:
                    Admin admin = new Admin("admin","password");
                    adminController.addAdmin(admin);
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
                    loop = movieMenu(br);
                case 2:
                    break;
                case 3:
                    System.out.println("Sure, returning back to main menu.");
                    loop = false;
                    break;
                case 4:
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
        System.out.println("---------------- Cinema List -----------------");
        System.out.println("----------------------------------------------");
        System.out.println("1. Shaw");
        System.out.println("2. Apple");
        System.out.println("3. Potato");
    }

    private static boolean movieMenu(BufferedReader br) throws IOException {
        boolean loop = true;
        printListMovieMenu();
        int index;
        int choice = Integer.parseInt(br.readLine());
        switch (choice) {
            case 1:
                System.out.println("Please enter the name of the Movie:");
                String movieTitle = br.readLine();
                //movieController.findMovieByTitle(movieTitle);
                Movie mov = movieController.getMovieByTitle(movieTitle);
                if(mov == null){
                    System.out.println("Movies doesn't exist in our database");
                }
                else{
                    System.out.println(mov.getTitle() + " (" + mov.getMovieDetails().getMovieStatus() + ")");
                }
                break;
            case 2:
                movieController.displayCurrentShowingMovie();
                System.out.println("Please select one of the movies:");
                choice = Integer.parseInt(br.readLine());
                loop = movieBookingsMenu(br, choice, "nowshowing");
                break;
            case 3:
                movieController.displayPreviewMovie();
                System.out.println("Please select one of the movies:");
                choice = Integer.parseInt(br.readLine());
                loop = movieBookingsMenu(br, choice, "preview");
                break;
            case 4:
                movieController.displayComingSoonMovie();
                break;
            case 5:
                movieController.displayEndedMovies();
                break;
            case 6:
                movieController.displayAllMovie();
//                System.out.println("Please enter the index of movie that you want to know more:");
//                index = Integer.parseInt(br.readLine());
//                movieController.showDetails(index);
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                System.out.println("Sure, returning back to main menu.");
                break;
            case 10:
                System.out.println("Exiting the program...");
                System.out.println("Goodbye!");
                return false;
            default:
                System.out.println("Please enter a valid choice!");
                break;
        }
        return true;
    }

    private static boolean movieBookingsMenu(BufferedReader br, int movieChoice, String movieStatus) throws IOException {
        boolean loop = true;
        int index;
        System.out.println();
        System.out.println("Please choose one of the following options");
        System.out.println();
        printListMovieOptionsMenu();
        String status;
        int choice = Integer.parseInt(br.readLine());

        switch (choice){
            case 1:
                if(movieStatus.toLowerCase().equals("nowshowing"))
                    movieController.showDetailsCurrentShowing(movieChoice);
                else if(movieStatus.toLowerCase().equals("preview"))
                    movieController.showDetailsPreviewShowing(movieChoice);
                break;
            case 2:
                loop = showtimeMenu(br);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                System.out.println("Sure, returning back to main menu.");
                break;
        }
        return true;
    }

    private static boolean showtimeMenu(BufferedReader br) throws IOException {
        int index;
        System.out.println();
        System.out.println("Please choose one of the following options");
        System.out.println();
        showtimeMenu();
        int choice = Integer.parseInt(br.readLine());

        switch (choice) {
            case 1:
                break;

            case 2:
                System.out.println("Input the date in this format (day/month/year) -> eg: 1/12/2019");
                String date = br.readLine();
                break;
            case 3:
                //display all showtime
                System.out.println("Please select one of the options.");
                choice = Integer.parseInt(br.readLine());
                break;
            case 4:
                System.out.println("Sure, returning back to main menu.");
                break;

        }
        return true;
    }

    private static void showtimeMenu(){
        System.out.println("----------------Showtime Menu -----------------");
        System.out.println("-----------------------------------------------");
        System.out.println("1. Filter by Cineplex"); // return all cineplex available (minimum 3)
        System.out.println("2. Filter by date");
        System.out.println("3. Show all");
        System.out.println("4. Go back to Main Menu");
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

    private static void printCustomerMenu() {
        System.out.println("----------------Customer Menu -----------------");
        System.out.println("-----------------------------------------------");
        System.out.println("1. List Movies");
        System.out.println("2. View booking history");
        System.out.println("3. Go back to Main Menu");
        System.out.println("4. Exit Menu");
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

    private static void printListMovieOptionsMenu(){
        System.out.println("1. View Movie Detail");
        System.out.println("2. Book Ticket");
        System.out.println("3. Leave Review");
        System.out.println("4. Read Reviews");
        System.out.println("5. Go back to Main Menu");
    }
}

