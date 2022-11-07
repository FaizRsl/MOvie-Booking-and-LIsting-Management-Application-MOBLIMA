package view;

import Controller.BookingController;
import Controller.CinemaController;
import Controller.MovieController;
import Controller.PriceController;
import Model.Booking.Booking;
import Model.Cinema.Showtime;
import Model.Movie.Movie;
import Model.Movie.MovieCensorship;
import Model.Seat.Seats;
import Model.Ticket.AdultTicket;
import Model.Ticket.ChildrenTicket;
import Model.Ticket.SeniorTicket;
import Model.Ticket.Ticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieView {

    //movie function
    public void findMovieByTitle(Movie movie) {
        String text = movie == null ? "Movie does not exist, try again!" : "Movie exists, book your ticket now!";
        System.out.println(text);
    }
    public void displayMovies(List<Movie> movieList) {
        System.out.println("Movie Title(s)");
        System.out.println("========================");
        for (int i = 0;i < movieList.size();i++) {
            System.out.println((i+1) + ")" + movieList.get(i).getTitle() + " (" + movieList.get(i).getMovieDetails().getMovieStatus() + ")");
        }
    }
    public void displayMovieDetails(int index,Movie movie) {
        System.out.printf("%d Movie Title: %s \n",index,movie.getTitle());
        System.out.println("===============================================");
        System.out.printf("Director: %s \n",movie.getDirector());
        System.out.print("Cast:");
        movie.getCasts().forEach(cast -> System.out.printf("%s,",cast));
        System.out.println();
        System.out.printf("Age Rating: %s \n",movie.getMovieDetails().getMovieCensorship());
        System.out.printf("Synopsis: %s \n",movie.getSynopsis());
        System.out.printf("Movie Status %s \n",movie.getMovieDetails().getMovieStatus());
    }
    public void displayReviewsByMovies(int index, Movie movie){
        System.out.printf("Movie Title: %s \n",movie.getTitle());
        if(movie.getReviews().size() == 0){
            System.out.println("No reviews for this current movie.");
            return;
        }
        for(int i=0; i<movie.getReviews().size(); i++){
            System.out.println("Review " + (i+1) + ":");
            System.out.println("Reviewer Name: " + movie.getReviews().get(i).getReviewerName());
            System.out.println("Rating: " + movie.getReviews().get(i).getRating());
            System.out.println("Review Content: " + movie.getReviews().get(i).getReviewContent());
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.println();
        }
    }
    public void displayUpdateOptions(){
        System.out.println("What would you like to update?");
        System.out.println("1) Movie Title");
        System.out.println("2) Director");
        System.out.println("3) Cast");
        System.out.println("4) Age Rating");
        System.out.println("5) Synopsis");
        System.out.println("6) Movie Status");

    }
    public void displayUpdateCastOptions(){
        System.out.println("1) Add Cast Member");
        System.out.println("2) Remove Cast Member");
        System.out.println("3) Change Cast Member's Name");
        System.out.println("4) Quit");
    }
    public void displayUpdateAgeRatingOptions(){
        System.out.println("New Age Rating: ");
        System.out.println("1) G");
        System.out.println("2) PG");
        System.out.println("3) PG13");
        System.out.println("4) NC16");
        System.out.println("5) M18");
        System.out.println("6) R21");

    }
    public void displayUpdateMovieStatusOptions(){
        System.out.println("New Movie Status: ");
        System.out.println("1) Coming Soon");
        System.out.println("2) Preview");
        System.out.println("3) Now Showing");
        System.out.println("4) Ending");
    }


}
