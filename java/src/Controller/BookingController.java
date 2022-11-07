package Controller;

import Model.Booking.Booking;
import Model.Movie.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BookingController {

    private List<Booking> bookings;

    public BookingController() {
        bookings = DatabaseController.getBookingFromDB();
    }

    public void bookingHistory(BufferedReader br) throws IOException {
        System.out.println("Please enter your username:");
        String username = br.readLine();
        List<Booking> bookingList = getBookingByUsername(username);
        if(bookingList.size() == 0)
            System.out.println("There is no booking for this user.");
        else
            while(true){
                System.out.println("You have " + bookingList.size() + " bookings.");
                System.out.println("Please select which bookings you wish to view (1-" +bookingList.size()+ "):");
                int choice = Integer.parseInt(br.readLine());
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
        DatabaseController.updateBookingDB(bookings);
    }

    public List<Booking> getBookingByUsername(String username){
        List<Booking> bookingList = new ArrayList<>();
        for(int i=(bookings.size()-1); i>=0; i--){
            if(bookings.get(i).getBuyerName().toLowerCase().equals(username))
                bookingList.add(bookings.get(i));
        }
        return bookingList;
    }

    public void getTopFiveMovieByTicketSales(){
        MovieController movieController = new MovieController();
        List<Movie> movieList = movieController.getMovies();
        Map<Movie, Double> moviePriceHM = new HashMap<>(movieList.size());

        double totalSales = 0.0;

        for(int i=0; i<movieList.size(); i++){
            moviePriceHM.put(movieList.get(i), 0.0);
        }
        for(int i=0; i<movieList.size(); i++){
            Movie movie = movieList.get(i);
            for(int j=0; j<bookings.size(); j++){
                if(bookings.get(j).getTickets().get(0).getShowtime().getMovie().getTitle().toLowerCase().equals(movie.getTitle().toLowerCase()))
                    totalSales += bookings.get(j).getPrice();
            }
            totalSales = Math.round(totalSales*100.00)/100.00;
            moviePriceHM.put(movie,moviePriceHM.get(movie) + totalSales);
            totalSales = 0.0;
        }

        class MoviePrice{
            String movieName;
            double totalPrice;
            MoviePrice(String movieName, double totalPrice){
                this.movieName = movieName;
                this.totalPrice = totalPrice;
            }
            public double getTotalPrice() {
                return totalPrice;
            }
            public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
            }
        }
        class priceComparator implements Comparator<MoviePrice>{
            public int compare(MoviePrice s1, MoviePrice s2) {
                if (s1.getTotalPrice() < s2.getTotalPrice())
                    return 1;
                else if (s1.getTotalPrice() > s2.getTotalPrice())
                    return -1;
                return 0;
            }
        }

        Queue<MoviePrice> pq = new PriorityQueue<>(movieList.size(), new priceComparator());

        for(int i = 0; i< moviePriceHM.size(); i++){
            MoviePrice moviePrice = new MoviePrice(movieList.get(i).getTitle(), moviePriceHM.get(movieList.get(i)));
            pq.add(moviePrice);
        }

        for(int i=0; i< 5; i++){
            MoviePrice mp = pq.poll();
            System.out.print(mp.movieName + ": ");
            System.out.println(mp.totalPrice);
        }


    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        DatabaseController.updateBookingDB(bookings);
    }

    public void updateBooking(Booking booking) {
        bookings = bookings.stream().filter(bookingItem -> !bookingItem.getId().equals(booking.getId())).collect(Collectors.toList());
        bookings.add(booking);
        DatabaseController.updateBookingDB(bookings);
    }
}
