package Controller;

import Model.Movie.*;
import view.MovieView;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MovieController {

    private DatabaseController databaseController = DatabaseController.getInstance();
    private List<Movie> movies;

    private MovieView movieView;

    public MovieController() {
        movieView = new MovieView();
        movies = databaseController.getMovieFromDB();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void createMovie(BufferedReader br) {
        try {
            Movie movie = movieView.displayCreateMovie(br);
            addMovie(movie);
            databaseController.updateMovieDB(movies);
        } catch (IOException e) {
            System.out.println("Unable to read the user input!");
            System.out.println("Error Message:" + e.getMessage());
        }
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
        databaseController.updateMovieDB(movies);
    }

    public void removeMovieByStatus(BufferedReader br) throws IOException {
        displayAllMovie();
        System.out.println("What movie is to be removed?");
        int remove = Integer.parseInt(br.readLine());

        movies.get(remove-1).getMovieDetails().setMovieStatus(MovieStatus.ENDED);
        databaseController.updateMovieDB(movies);
    }

    public void updateMovieDetailsFromInput(BufferedReader br) {
        try {
            Movie movie = movieView.displayUpdateMovie(br, movies);
            updateMovieDetails(movie);
        } catch (IOException e) {
            System.out.println("Unable to read the user input");
            System.out.println("Error Message:" + e.getMessage());
        }

    }
    public void updateMovieDetails(Movie movie) {
        movies.remove(movie);
        movies.add(movie);
        databaseController.updateMovieDB(movies);
    }

    public Movie getMovieByTitle(String movieTitle) {
        return movies.stream().filter(movie -> movie.getTitle().equalsIgnoreCase(movieTitle)).findFirst().orElse(null);
    }

    public void displayAllMovie() {
        movieView.displayMovies(movies);
    }

    public void showDetails(int choice) {
        if (choice <= movies.size()) {
         movieView.displayMovieDetails(choice,movies.get(choice-1));
        }
    }

    public void showReviewsByMovie(int choice){
        ArrayList<Movie> showMovies = getMovieAccordingToStatus(MovieStatus.NOWSHOWING);
        if (choice <= showMovies.size()) {
            movieView.displayReviewsByMovies(showMovies.get(choice-1));
        }
    }

    public void addReviews(String name, double rating, String reviewContent, int movieChoice, MovieStatus status){
        Review review = new Review(name, rating, reviewContent);
        Movie movie = getMovieByStatusAndIndex(status, movieChoice);
        System.out.println(movie.getTitle());
        movie.getReviews().add(review);
        double overallRating = Math.round(getOverallRating(movie) * 100.0)/100.0;
        movie.setRating(overallRating);
        System.out.println(movie.getReviews().size());
        updateMovieDetails(movie);
    }

    public double getOverallRating(Movie movie){
        double total = 0;
        int count = 0;
        for(int i=0; i<movie.getReviews().size(); i++){
            total += movie.getReviews().get(i).getRating();
            count++;
        }
        return (total/count);
    }

    public void getTopFiveMovieRating(){
        List<Movie> movieList = movies;
        movieList.sort(Comparator.comparing(Movie::getRating));
        List<Movie> topFive = movieList.subList(0,4);
        movieView.displayMovieWithReviews(topFive);
    }

    public Movie getMovieByStatusAndIndex(MovieStatus movieStatus, int movieIndex){
        switch(movieStatus) {
            case PREVIEW:
            case NOWSHOWING:
                if (getMovieAccordingToStatus(movieStatus).size() < movieIndex-1)
                    return null;
                return getMovieAccordingToStatus(movieStatus).get(movieIndex-1);
            default:
                return null;
        }
    }

    public void showDetails(int choice, MovieStatus movieStatus) {
        ArrayList<Movie> movies = getMovieAccordingToStatus(movieStatus);
        if (choice <= movies.size()) {
            movieView.displayMovieDetails(choice, movies.get(choice-1));
        }
    }

    public void displayAccordingtoMovieStatus(MovieStatus movieStatus) {
        ArrayList<Movie> currentShowing = getMovieAccordingToStatus(movieStatus);
        movieView.displayMovies(currentShowing);
    }

    private ArrayList<Movie> getMovieAccordingToStatus(MovieStatus movieStatus) {
        return movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() == movieStatus).collect(Collectors.toCollection(ArrayList::new));
    }

    private void addMovie(Movie movie) {
        movies.add(movie);
    }

}
