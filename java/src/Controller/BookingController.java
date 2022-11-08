package Controller;

import Model.Booking.Booking;
import Model.Movie.Movie;
import Model.Ticket.Ticket;
import view.BookingView;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BookingController {

    private DatabaseController databaseController = DatabaseController.getInstance();

    private BookingView bookingView;

    private List<Booking> bookings;

    public BookingController() {
        bookingView = new BookingView();
        bookings = databaseController.getBookingFromDB();
    }

    public void bookingHistory(Scanner sc) {
        System.out.println("Please enter your username:");
        String username = sc.nextLine();
        List<Booking> bookingList = getBookingByUsername(username);
        if(bookingList.size() == 0)
            System.out.println("There is no booking for this user.");
        else
            while(true){
                System.out.println("You have " + bookingList.size() + " bookings.");
                System.out.println("Please select which bookings you wish to view (1-" +bookingList.size()+ "):");
                int choice = sc.nextInt();
                if((choice-1) < 0 || choice > bookingList.size()){
                    System.out.println("Invalid input. Please try again");
                }
                else{
                    System.out.println(bookingList.get(choice-1).toString());
                    break;
                }
            }
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        databaseController.updateBookingDB(bookings);
    }

    public List<Booking> getBookingByUsername(String username){
        return findBookingByUsername(username);
    }

    public void getTopFiveMovieByTicketSales(){
        MovieController movieController = new MovieController();
        List<Movie> movieList = movieController.getMovies();
        Map<String, Double> moviePriceHM = new HashMap<>(movieList.size());
        double totalSales = 0.0;
        for (Movie movie : movieList) {
            moviePriceHM.put(movie.getTitle(),totalSales);
        }
        for (Booking booking : bookings) {
            for (Ticket ticket : booking.getTickets()) {
                totalSales += ticket.getPrice();
                moviePriceHM.put(ticket.getShowtime().getMovie().getTitle(),moviePriceHM.get(ticket.getShowtime().getMovie().getTitle()) + totalSales);
            }
            totalSales = 0;
        }
        SortedMap<String, Double> moviePrice = moviePriceHM.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, TreeMap::new));
        List<String> movies = new ArrayList<>(moviePrice.keySet()).subList(0,5);
        List<Double> salesPriceList = new ArrayList<>(moviePrice.values()).subList(0,5);
        bookingView.displayTopFiveMovies(movies,salesPriceList);
    }

    private List<Booking> findBookingByUsername(String username) {
        List<Booking> bookingList = new ArrayList<>();
        for(int i=(bookings.size()-1); i>=0; i--){
            if(bookings.get(i).getBuyerName().equalsIgnoreCase(username))
                bookingList.add(bookings.get(i));
        }
        return bookingList;
    }
}
