package view;

import Controller.BookingController;
import Controller.CinemaController;
import Controller.MovieController;
import Controller.PriceController;
import Model.Booking.Booking;
import Model.Cinema.Showtime;
import Model.Movie.Movie;
import Model.Seat.Seats;
import Model.Ticket.AdultTicket;
import Model.Ticket.ChildrenTicket;
import Model.Ticket.SeniorTicket;
import Model.Ticket.Ticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerView {

    //menus
    public static boolean customerMenu(BufferedReader br, MovieController movieController, CinemaController cinemaController, BookingController bookingController) throws IOException {
        boolean loop = true;
        int choice;
        while(loop){
            printCustomerMenu();
            choice = Integer.parseInt(br.readLine());
            switch(choice) {
                case 1:
                    loop = movieMenu(br, movieController, cinemaController, bookingController);
                    break;
                case 2:
                    bookingController.bookingHistory(br);
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
        }
        return true;
    }
    private static boolean movieMenu(BufferedReader br, MovieController movieController, CinemaController cinemaController, BookingController bookingController) throws IOException {
        boolean loop = true;
        int choice;

        while(loop){
            printListMovieMenu();
            choice = Integer.parseInt(br.readLine());
            switch(choice){
                case 1:
                    movieController.searchMovie(br);
                    break;
                case 2:
                    loop = listMovieByStatus(br, "nowshowing", movieController, cinemaController, bookingController);
                    break;
                case 3:
                    loop = listMovieByStatus(br, "preview", movieController, cinemaController, bookingController);
                    break;
                case 4:
                    loop = listMovieByStatus(br, "comingsoon", movieController, cinemaController, bookingController);
                    break;
                case 5:
                    loop = listMovieByStatus(br, "all", movieController, cinemaController, bookingController);
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
                    loop = false;
                    break;
                case 10:
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
    private static boolean listMovieByStatus(BufferedReader br, String status, MovieController movieController, CinemaController cinemaController, BookingController bookingController) throws IOException{
        boolean loop = true;
        int max = 0;
        int choice = -1;
        if(status.equals("nowshowing")){
            max = movieController.displayCurrentShowingMovie();
        }
        else if(status.equals("preview")){
            max = movieController.displayPreviewMovie();
        }
        else if(status.equals("comingsoon")){
            max = movieController.displayComingSoonMovie();
        }
        else if(status.equals("all")){
            max = movieController.displayEndedMovies();
        }

        while(choice == -1){
            System.out.println("Please select one of the movies:");
            choice = Integer.parseInt(br.readLine());
            if(choice<1 || choice>max){
                System.out.println("Invalid input. Please try again!");
                choice = -1;
            }
            else
                break;
        }

        if(status.equals("ended")) {
            loop = endedMovieBookingsMenu(br, choice, status, movieController);
        }
        else if(status.equals("comingsoon")){
            loop = comingSoonBookingsMenu(br, choice, status, movieController);
        }
        else
            loop = movieBookingsMenu(br, choice, status, movieController, cinemaController, bookingController);

        return loop;
    }
    private static boolean comingSoonBookingsMenu(BufferedReader br, int movieChoice, String movieStatus, MovieController movieController) throws IOException {
        boolean loop = true;
        int choice;
        while (loop){
            printListComingSoonMovieOptionMenu();
            choice = Integer.parseInt(br.readLine());
            switch (choice){
                case 1:
                    movieController.showDetailsComingSoon(movieChoice);
                    break;
                case 2:
                    System.out.println("Sure, returning back to main menu.");
                    loop = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice!");
                    break;
            }
        }
        return true;
    }
    private static boolean endedMovieBookingsMenu(BufferedReader br, int movieChoice, String movieStatus, MovieController movieController) throws IOException {
        boolean loop = true;
        int choice;
        while (loop){
            printListEndedMovieOptionsMenu();
            choice = Integer.parseInt(br.readLine());
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
                    loop = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice!");
                    break;
            }
        }
        return true;
    }
    private static boolean movieBookingsMenu(BufferedReader br, int movieChoice, String movieStatus, MovieController movieController, CinemaController cinemaController, BookingController bookingController) throws IOException {
        boolean loop = true;
        int choice;

        while(loop){
            System.out.println("Please choose one of the following options");
            printListMovieOptionsMenu();
            choice = Integer.parseInt(br.readLine());
            switch (choice){
                case 1:
                    if(movieStatus.toLowerCase().equals("nowshowing"))
                        movieController.showDetailsCurrentShowing(movieChoice);
                    else if(movieStatus.toLowerCase().equals("preview"))
                        movieController.showDetailsPreviewShowing(movieChoice);
                    break;
                case 2:
                    loop = showtimeMenu(br, movieChoice, movieStatus, movieController, cinemaController, bookingController);
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
                    loop = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice!");
                    break;
            }
        }
        return true;
    }
    private static boolean showtimeMenu(BufferedReader br, int movieChoice, String movieStatus, MovieController movieController, CinemaController cinemaController, BookingController bookingController) throws IOException {
        boolean loop = true;
        int choice;

        while(loop){
            System.out.println("Please choose one of the following options");
            showtimePrintMenu();
            choice = Integer.parseInt(br.readLine());
            switch (choice){
                case 1:
                    handleShowtimeDisplay(br, choice, movieChoice, movieStatus, movieController, cinemaController, bookingController);
                    break;
                case 2:
                    handleShowtimeDisplay(br, choice, movieChoice, movieStatus, movieController, cinemaController, bookingController);
                    break;
                case 3:
                    handleShowtimeDisplay(br, choice, movieChoice, movieStatus, movieController, cinemaController, bookingController);
                    break;
                case 4:
                    System.out.println("Sure, returning back to main menu.");
                    loop = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice!");
                    break;
            }
        }
        return true;
    }
    private static void handleShowtimeDisplay(BufferedReader br, int choice, int movieChoice, String movieStatus, MovieController movieController, CinemaController cinemaController, BookingController bookingController) throws IOException {
        Movie mov;
        String date = "";
        int totalCount = 0;
        int cinemaChoice = -1;
        if(choice == 1){
            totalCount = cinemaController.displayAllCineplex();
            while(cinemaChoice == -1){
                System.out.println("Choose a cineplex:");
                cinemaChoice = Integer.parseInt(br.readLine());
                if(cinemaChoice<1 || cinemaChoice>totalCount){
                    System.out.println("Invalid input. Please try again!");
                    cinemaChoice = -1;
                }
                else
                    break;
            }
            mov = movieController.getMovieByStatusAndIndex(movieStatus,movieChoice);
            totalCount = cinemaController.displayShowtimeByCineplex(choice, mov.getTitle());
            if(totalCount != 0){
                handleShowtime(br,totalCount, choice, mov, date, cinemaChoice, cinemaController, bookingController);
            }
        }
        else if(choice == 2){
            System.out.println("Input the date in this format (day/month/year) -> eg: 24/10/2022");
            date = br.readLine();
            mov = movieController.getMovieByStatusAndIndex(movieStatus,movieChoice);
            totalCount = cinemaController.displayShowtimeByDate(date, mov.getTitle());
            if(totalCount != 0){
                handleShowtime(br,totalCount, choice, mov, date, cinemaChoice, cinemaController, bookingController);
            }
        }
        else if(choice == 3){
            mov = movieController.getMovieByStatusAndIndex(movieStatus,movieChoice);
            totalCount = cinemaController.displayAllCineplexShowtimes(mov.getTitle());
            if(totalCount != 0){
                handleShowtime(br, totalCount, choice, mov, date, cinemaChoice, cinemaController, bookingController);
            }
        }
    }
    private static void handleShowtime(BufferedReader br, int totalCount, int choice, Movie mov, String date, int cinemaChoice, CinemaController cinemaController, BookingController bookingController) throws IOException {
        int showtimeChoice;

        if(choice == 1){ //filterbycineplex
            showtimeChoice = getShowtimeChoice(totalCount);
            Showtime showtimeSelected = cinemaController.getShowtimeByCineplex(cinemaChoice, showtimeChoice, mov.getTitle());
            handleShowtimeBySelection(showtimeSelected, bookingController, cinemaController);

        }
        else if(choice == 2){ //filterbydate
            showtimeChoice = getShowtimeChoice(totalCount);
            Showtime showtimeSelected = cinemaController.getShowtimeByDate(showtimeChoice, date, mov.getTitle());
            handleShowtimeBySelection(showtimeSelected, bookingController, cinemaController);
        }
        else if(choice == 3){ //filterall
            showtimeChoice = getShowtimeChoice(totalCount);
            Showtime showtimeSelected = cinemaController.getCineplexAllShowtimes(showtimeChoice, mov.getTitle());
            handleShowtimeBySelection(showtimeSelected, bookingController, cinemaController);
        }


    }
    private static void handleShowtimeBySelection(Showtime showtimeSelected, BookingController bookingController, CinemaController cinemaController){
        boolean loop = true;
        Scanner sc = new Scanner(System.in);
        int choice;
        int seatNumBookings = 20;
        if(showtimeSelected != null)
        {
            while(loop){
                showtimeSelected.getSeatLayout().printSeatLayout();
                printUserBookingsMenu();
                choice = sc.nextInt();
                switch (choice){
                    case 1:
                        while(seatNumBookings>5) {
                            System.out.println("Select number of seats to book:");
                            seatNumBookings = sc.nextInt();
                            if(seatNumBookings<1){
                                System.out.println("Invalid input, please try again.");
                                seatNumBookings = 6;
                            }
                            else if(seatNumBookings>5){
                                System.out.println("Max number of bookings is 5 per person.");
                                seatNumBookings = 6;
                            }
                        }
                        ArrayList<Seats> seats = new ArrayList<>();
                        for(int i=0; i<seatNumBookings; i++){
                            seats.add(handleSeatBookings(showtimeSelected));
                        }
                        purchaseTicket(seatNumBookings, seats, showtimeSelected, bookingController, cinemaController);
                        loop = false;
                        break;
                    case 2:
                        System.out.println("Going back to Showtime page");
                        loop = false;
                        break;
                    default:
                        System.out.println("Please enter a valid choice!");
                        break;
                }
            }
        }
    }

    //bookings function
    private static int getShowtimeChoice(int totalCount){
        Scanner sc = new Scanner(System.in);
        int showtimeChoice = -1;
        while(showtimeChoice == -1){
            System.out.println("Choose one of the showtime available:");
            showtimeChoice = sc.nextInt();
            if(showtimeChoice<1 || showtimeChoice>totalCount){
                System.out.println("Invalid input. Please try again!");
                showtimeChoice = -1;
            }
            else
                break;
        }
        return showtimeChoice;
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
    private static void purchaseTicket(int size, ArrayList<Seats> seats, Showtime showtime, BookingController bookingController, CinemaController cinemaController){
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
                printOrder(tickets, totalPrice, cinemaController);
                break;
            }
        }

    }
    private static void printOrder(ArrayList<Ticket> tickets, double totalPrice, CinemaController cinemaController){
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

    //printMenus
    private static void printListComingSoonMovieOptionMenu(){
        System.out.println("1. View Movie Detail");
        System.out.println("2. Go back to Main Menu");
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
    private static void printUserBookingsMenu(){
        System.out.println("----------------Booking Menu -----------------");
        System.out.println("----------------------------------------------");
        System.out.println("1. Book Seat");
        System.out.println("2. Go back to Showtime Menu");
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
    private static void showtimePrintMenu(){
        System.out.println("----------------Showtime Menu -----------------");
        System.out.println("-----------------------------------------------");
        System.out.println("1. Filter by Cineplex"); // return all cineplex available (minimum 3)
        System.out.println("2. Filter by date");
        System.out.println("3. Show all");
        System.out.println("4. Go back to Main Menu");
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
}
