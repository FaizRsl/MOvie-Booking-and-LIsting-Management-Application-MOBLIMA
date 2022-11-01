package view;

import Model.Movie.Movie;

import java.util.ArrayList;

public class MovieView {
    public void findMovieByTitle(Movie movie) {
        String text = movie == null ? "Movie does not exist, try again!" : "Movie exists, book your ticket now!";
        System.out.println(text);
    }

    public void displayAllMovies(ArrayList<Movie> movieList) {
        System.out.println("Movie Title(s)");
        System.out.println("========================");
        for (int i = 0;i < movieList.size();i++) {
            System.out.println((i+1) + ")" + movieList.get(i).getTitle());
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
        System.out.printf("Genre: %s",movie.getMovieDetails().getMovieType());
        System.out.printf("Movie Status %s \n",movie.getMovieDetails().getMovieStatus());
    }
}
