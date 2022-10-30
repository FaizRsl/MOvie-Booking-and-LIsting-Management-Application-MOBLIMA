import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MOBLIMA {

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
            StaffLogin sl = new StaffLogin();
            loggedin = sl.login(username,password);
            if(loggedin) {
                System.out.println("Login Successful");
            } else {
                System.out.println("Invalid username or password, please try again.");
            }
        }

        Movie movie = new Movie();

        while (loop) {
            printAdminMenu();
            int choice = Integer.parseInt(br.readLine());
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
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
        SerializeDB movieDB = new SerializeDB("Movie.dat");
        List<Movie> movieList = movieDB.getObjectsList();
        Cinema cinema = new Cinema(movieList);
        //System.out.println(cinema.getMovieList().get(1).getTitle());
        do {
            printCustomerMenu();
            int choice = Integer.parseInt(br.readLine());
            switch(choice) {
                case 1:
                    cinema.listAllMovies();
                    break;
                case 2:
                    System.out.println("Please enter movie name:");
                    String movieName = br.readLine();
                    cinema.findMovie(movieName);
                    break;
                case 3:
                    cinema.displayAllMovies();
                    break;
                case 4:
                    System.out.println("Select movies: ");
                    cinema.listAllMovies();
                    int movieChoice = Integer.parseInt(br.readLine());
                    System.out.println("Select ticket type(Senior, Student, Weekdays, Weekends, PH): ");
                    String ticketType = br.readLine();
                    System.out.println("Check seat availability and select seat");
                    System.out.println("Total seats available are: " + cinema.getSeats().getTotalSeats());
                    System.out.println("Please enter the number of seats you would like to book:");
                    int seatsNum = Integer.parseInt(br.readLine());
                    int[] list = cinema.getSeats().bookSeats(seatsNum);
                    String title = cinema.getMovieList().get(movieChoice).getTitle();
                    for(int i=0; i<seatsNum; i++){
                        Ticket ticket = new Ticket(list[i], title, ticketType);
                        //add code to store ticket to db
                    }
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
        System.out.println("2. Search Movie");
        System.out.println("3. View Movie Details");
        System.out.println("4. Book and purchase ticket");
        System.out.println("5. View booking history");
        System.out.println("6. List Top 5 ranking by ticket sales");
        System.out.println("7. List Top 5 ranking by overall reviewers' rating");
        System.out.println("8. Go back to Main Menu");
        System.out.println("9. Exit Menu");
        System.out.println("-----------------------------------------------");
        System.out.println("Please enter your choice:");
    }
}

