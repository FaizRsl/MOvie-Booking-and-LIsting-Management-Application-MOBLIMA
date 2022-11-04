import Controller.*;
import Model.Booking.Booking;
import Model.Cinema.CinemaClass;
import Model.Cinema.Showtime;
import Model.Movie.Movie;
import Model.Movie.MovieType;
import Model.Seat.SeatLayout;
import Model.Seat.Seats;
import Model.Ticket.AdultTicket;
import Model.Ticket.ChildrenTicket;
import Model.Ticket.SeniorTicket;
import Model.Ticket.Ticket;
import Model.User.Admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MOBLIMA {

    private static MovieController movieController = new MovieController();
    private static AdminController adminController = new AdminController();

    private static CinemaController cinemaController = new CinemaController();

    private static BookingController bookingController = new BookingController();

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
                    break;
                case 5: // update showtime
                    break;
                case 6: // remove showtime
                    break;
                case 7: //Configure System Settings
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
                    break;
                case 2:
                    System.out.println("Please enter your username:");
                    String username = br.readLine();
                    List<Booking> bookingList = bookingController.getBookingByUsername(username);
                    if(bookingList.size() == 0)
                        System.out.println("There is no booking for this user.");
                    else
                        while(true){
                            System.out.println("You have " + bookingList.size() + " bookings.");
                            System.out.println("Please select which bookings you wish to view (1-" +bookingList.size()+ "):");
                            choice = Integer.parseInt(br.readLine());
                            if((choice-1) < 0 || choice > bookingList.size()){
                                System.out.println("Invalid input. Please try again");
                            }
                            else{
                                System.out.println(bookingList.get(choice-1).toString());
                                break;
                            }
                        }
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
                System.out.println("Please select one of the movies:");
                choice = Integer.parseInt(br.readLine());
                loop = endedMovieBookingsMenu(br, choice, "ended");
                break;
            case 6:
                movieController.displayAllMovie();
                break;
            case 7:
                movieController.getTopFiveMovieRating();
                break;
            case 8:
                bookingController.getTopFiveMovieByTicketSales();
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
                loop = showtimeMenu(br, movieChoice, movieStatus);
                break;
            case 3:
                System.out.println("Please enter your name:");
                String name = br.readLine();
                System.out.println("Please enter your rating(1-5):");
                double rating = Double.parseDouble(br.readLine());
                System.out.println("Please enter your review:");
                String reviewContent = br.readLine();
                movieController.addReviews(name, rating, reviewContent, movieChoice, movieStatus);
                break;
            case 4:
                movieController.showReviewsByMovie(movieChoice);
                break;
            case 5:
                System.out.println("Sure, returning back to main menu.");
                break;
        }
        return true;
    }

    private static boolean showtimeMenu(BufferedReader br, int movieChoice, String movieStatus) throws IOException {
        boolean loop = true;
        System.out.println();
        System.out.println("Please choose one of the following options");
        System.out.println();
        showtimeMenu();
        Movie mov;
        Showtime showtimeSelected;
        int choice = Integer.parseInt(br.readLine());

        switch (choice) {
            case 1:
                cinemaController.displayAllCineplex();
                System.out.println("Choose a cineplex:");
                choice = Integer.parseInt(br.readLine());
                mov = movieController.getMovieByStatusAndIndex(movieStatus,movieChoice);
                cinemaController.displayShowtimeByCineplex(choice, mov.getTitle());
                System.out.println("Choose one of the showtime available:");
                int showtimeChoice = Integer.parseInt(br.readLine());
                showtimeSelected = cinemaController.getShowtimeByCineplex(choice, showtimeChoice, mov.getTitle());
                if(showtimeSelected != null)
                {
                    showtimeSelected.getSeatLayout().printSeatLayout();
                    printUserBookingsMenu();
                    choice = Integer.parseInt(br.readLine());
                    if(choice == 2)
                        break;
                    System.out.println("Select number of seats to book:");
                    int seatNumBookings = Integer.parseInt(br.readLine());
                    ArrayList<Seats> seats = new ArrayList<>();
                    for(int i=0; i<seatNumBookings; i++){
                        seats.add(handleSeatBookings(showtimeSelected));
                    }
                    purchaseTicket(seatNumBookings, seats, showtimeSelected);
                }
                break;
            case 2:
                System.out.println("Input the date in this format (day/month/year) -> eg: 24/10/2022");
                String date = br.readLine();
                mov = movieController.getMovieByStatusAndIndex(movieStatus,movieChoice);
                cinemaController.displayShowtimeByDate(date, mov.getTitle());
                System.out.println("Choose one of the showtime available:");
                choice = Integer.parseInt(br.readLine());
                showtimeSelected = cinemaController.getShowtimeByDate(choice, date, mov.getTitle());
                if(showtimeSelected != null)
                {
                    showtimeSelected.getSeatLayout().printSeatLayout();
                    printUserBookingsMenu();
                    choice = Integer.parseInt(br.readLine());
                    if(choice == 2)
                        break;
                    System.out.println("Select number of seats to book:");
                    int seatNumBookings = Integer.parseInt(br.readLine());
                    ArrayList<Seats> seats = new ArrayList<>();
                    for(int i=0; i<seatNumBookings; i++){
                        seats.add(handleSeatBookings(showtimeSelected));
                    }
                    purchaseTicket(seatNumBookings, seats, showtimeSelected);
                }
                break;
            case 3:
                //display all showtime
                mov = movieController.getMovieByStatusAndIndex(movieStatus,movieChoice);
                cinemaController.displayAllCineplexShowtimes(mov.getTitle());
                System.out.println("Choose one of the showtime available:");
                choice = Integer.parseInt(br.readLine());
                showtimeSelected = cinemaController.getCineplexAllShowtimes(choice, mov.getTitle());
                showtimeSelected.getSeatLayout().printSeatLayout();
                printUserBookingsMenu();
                choice = Integer.parseInt(br.readLine());
                if(choice == 2)
                    break;
                System.out.println("Select number of seats to book:");
                int seatNumBookings = Integer.parseInt(br.readLine());
                ArrayList<Seats> seats = new ArrayList<>();
                for(int i=0; i<seatNumBookings; i++){
                    seats.add(handleSeatBookings(showtimeSelected));
                }
                purchaseTicket(seatNumBookings, seats, showtimeSelected);
                break;
            case 4:
                System.out.println("Sure, returning back to main menu.");
                break;

        }
        return true;
    }

    private static Seats handleSeatBookings(Showtime showtime){

        int row;
        int col;
        Scanner sc = new Scanner(System.in);
        Seats chosenSeats;

        while(true){
            try{
                System.out.println("Please enter a seat number: Eg (B4) to book row 2 and column 4");
                String seatNumber = sc.nextLine();
                row = Character.toLowerCase(seatNumber.substring(0,1).toCharArray()[0])  - 'a';
                col = Integer.parseInt(seatNumber.substring(1,seatNumber.length() >= 3 ? 3 : 2)) - 1;
            }catch(Exception e){
                System.out.println("Wrong format, try again\n");
                continue;
            }

            if ((col>=showtime.getSeatLayout().getCols())||(row >= showtime.getSeatLayout().getRows())||(col<0)||(row<0)){
                System.out.println("Seat does not exist, please try again!");
                continue;
            }

            if ((showtime.getSeatLayout().getSeats(row,col) != null)) {

                if (showtime.getSeatLayout().getSeats(row,col).isBooked()){
                    System.out.println("The seat is already book, input another seat");
                    continue;
                }else{
                    showtime.getSeatLayout().getSeats(row,col).bookSeat();
                    chosenSeats = new Seats(row,col);
                }
            }else{
                System.out.println("The seat is not among the choices");
                continue;
            }

            showtime.getSeatLayout().printSeatLayout();
            return chosenSeats;
        }

    }

    private static void purchaseTicket(int size, ArrayList<Seats> seats, Showtime showtime){
        Scanner sc = new Scanner(System.in);
        while(true){
            int count = 0;
            double totalPrice = 0;
            ArrayList<Ticket> tickets = new ArrayList<>(size);
            System.out.println("----------------Purchase Ticket -----------------");
            System.out.println("-------------------------------------------------");
            System.out.println("How many adults?");
            int adultCount = sc.nextInt();
            System.out.println("How many children?");
            int childrenCount = sc.nextInt();
            System.out.println("How many senior citizen?");
            int seniorCitizenCount = sc.nextInt();
            sc.nextLine();
            int totalCount = adultCount + childrenCount + seniorCitizenCount;
            if(totalCount != size) {
                System.out.println("Number does not tally with total tickets! Please try again.");
                continue;
            }

            if(totalCount == size) {
                for(int i = 0; i < adultCount; i++){
                    Ticket ticket = new AdultTicket(seats.get(count), showtime);
                    totalPrice += ticket.getPrice();
                    tickets.add(ticket);
                    count++;
                }

                for(int i = 0; i< childrenCount; i++){
                    Ticket ticket = new ChildrenTicket(seats.get(count), showtime);
                    totalPrice += ticket.getPrice();
                    tickets.add(ticket);
                    count++;
                }

                for(int i = 0; i< seniorCitizenCount; i++){
                    Ticket ticket = new SeniorTicket(seats.get(count), showtime);
                    totalPrice += ticket.getPrice();
                    tickets.add(ticket);
                    count++;
                }
                System.out.println("Please enter your name:");
                String userName = sc.nextLine();
                System.out.println("Please enter your email:");
                String userEmail = sc.nextLine();
                Booking booking = new Booking(tickets, userName, userEmail); //add this to database
                bookingController.addBooking(booking);
                totalPrice = Math.round(totalPrice * 100.00)/100.00;
                printOrder(tickets, totalPrice);
                break;
            }
        }

    }

    private static void printOrder(ArrayList<Ticket> tickets, double totalPrice){
        System.out.println("----------------ORDERS -----------------");
        System.out.println("----------------------------------------");
        for(int i=0; i<tickets.size(); i++){
            System.out.println("Seat ID: " + tickets.get(i).getSeats().toString());
            System.out.println("Price: " + tickets.get(i).getPrice());
            System.out.println("Showtime: ");
            cinemaController.displayShowtimeInfo(tickets.get(i).getShowtime());
        }

        System.out.println("Total price before GST: " + totalPrice);
        double totalPriceWithGst = Math.round(PriceController.GSTCalculation(totalPrice) * 100.00)/100.00;
        System.out.println("GST: " + Math.round((totalPriceWithGst - totalPrice) * 100.0)/100.00);
        System.out.println("Total Price: " + totalPriceWithGst);
        System.out.println("----------------------------------------");

    }

    private static void unbookedSeat(){

    }

//    private static boolean userBookingsMenu(BufferedReader br, int movieChoice, String movieStatus) throws IOException {
//        System.out.println();
//        System.out.println("Please choose one of the following options");
//        System.out.println();
//        printUserBookingsMenu();
//        int choice = Integer.parseInt(br.readLine());
//
//        switch (choice) {
//        }
//        return true;
//    }
//
    private static void printUserBookingsMenu(){
        System.out.println("----------------Booking Menu -----------------");
        System.out.println("----------------------------------------------");
        System.out.println("1. Book Seat");
        System.out.println("2. Go back to Main Menu");
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


    private static boolean endedMovieBookingsMenu(BufferedReader br, int movieChoice, String movieStatus) throws IOException {
        boolean loop = true;
        int index;
        System.out.println();
        System.out.println("Please choose one of the following options");
        System.out.println();
        printListEndedMovieOptionsMenu();
        String status;
        int choice = Integer.parseInt(br.readLine());

        switch (choice){
            case 1:
                    movieController.showDetailsEndedShowing(movieChoice);
                break;
            case 2:
                System.out.println("Please enter your name:");
                String name = br.readLine();
                System.out.println("Please enter your rating(1-5):");
                double rating = Double.parseDouble(br.readLine());
                System.out.println("Please enter your review:");
                String reviewContent = br.readLine();
                movieController.addReviews(name, rating, reviewContent, movieChoice, movieStatus);
                break;
            case 3:
                movieController.showReviewsByMovie(movieChoice);
                break;
            case 4:
                System.out.println("Sure, returning back to main menu.");
                break;
        }
        return true;
    }

    private static void printListComingSoonMovieOptionMenu(){
        System.out.println("1. View Movie Detail");
        System.out.println("4. Go back to Main Menu");
    }
    private static void printListEndedMovieOptionsMenu(){
        System.out.println("1. View Movie Detail");
        System.out.println("2. Leave Review");
        System.out.println("3. Read Reviews");
        System.out.println("4. Go back to Main Menu");
    }

    private static void printListMovieOptionsMenu(){
        System.out.println("1. View Movie Detail");
        System.out.println("2. Book Ticket");
        System.out.println("3. Leave Review");
        System.out.println("4. Read Reviews");
        System.out.println("5. Go back to Main Menu");
    }
}

