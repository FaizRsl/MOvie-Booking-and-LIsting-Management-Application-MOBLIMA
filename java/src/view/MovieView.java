package view;

import Model.Movie.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieView {
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
}
