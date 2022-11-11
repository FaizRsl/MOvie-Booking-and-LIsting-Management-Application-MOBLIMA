package Controller;

import Model.Movie.*;
import view.MovieView;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieController {

    private DatabaseController databaseController = DatabaseController.getInstance();
    private List<Movie> movies;

    private MovieView movieView;

    public MovieController() {
        movieView = new MovieView();
        movies = databaseController.getMovieFromDB();
    }

    public List<Movie> getMovies() {
        movies = databaseController.getMovieFromDB();
        return movies;
    }

    public List<Movie> getCurrentlyAvailableMovies() {
        return Stream.of(getMovieAccordingToStatus(MovieStatus.NOWSHOWING),getMovieAccordingToStatus(MovieStatus.PREVIEW))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void createMovie(Scanner sc) {
        Movie movie = movieView.displayCreateMovie(sc);
        addMovie(movie);
        databaseController.updateMovieDB(movies);
    }

    public void removeMovieByStatus(Scanner sc) throws InputMismatchException {
        displayAllMovie();
        System.out.println("What movie is to be removed?");
        int remove = sc.nextInt();
        if (remove > movies.size())
            System.out.println("Input is out of range! Please enter a valid range");
        movies.get(remove-1).getMovieDetails().setMovieStatus(MovieStatus.ENDED);
        databaseController.updateMovieDB(movies);
    }

    public void updateMovieDetailsFromInput(Scanner sc) {
        int update = 1;
        System.out.println("Which movie to update?");
        movieView.displayMovies(movies);
        try {
            update = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again!");
        }
        if (update > movies.size()) {
            System.out.println("Invalid input!");
            return;
        }
        Movie movie = movieView.displayUpdateMovie(sc,update, movies);
        if (movie != null)
            updateMovieDetails(update,movie);

    }

    public int displayMovieFromMovieList(List<Movie> movieList) {
        movieView.displayMovies(movieList);
        return movieList.size();
    }
    public int displayCurrentShowingMovie() {
        return displayAccordingtoMovieStatus(MovieStatus.NOWSHOWING);
    }
    public int displayPreviewMovie() {
        return displayAccordingtoMovieStatus(MovieStatus.PREVIEW);
    }

    public int displayComingSoonMovie() {
        return displayAccordingtoMovieStatus(MovieStatus.COMINGSOON);
    }

    public int displayEndedMovies() {
        return displayAccordingtoMovieStatus(MovieStatus.ENDED);
    }

    public void displayAllMovie() {
        movieView.displayMovies(movies);
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
        updateMovieDetails(movieChoice,movie);
    }

    public void getTopFiveMovieRating(){
        List<Movie> movieList = movies;
        movieList.sort(Collections.reverseOrder(Comparator.comparing(Movie::getRating)));
        List<Movie> topFive = movieList.subList(0,5);
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

    public void searchMovie(Scanner sc){
        System.out.println("Please enter the name of the Movie:");
        String movieTitle = sc.nextLine();
        Movie mov = getMovieByTitle(movieTitle);
        if(mov == null){
            System.out.println("Movies doesn't exist in our database");
        }
        else{
            System.out.println(mov.getTitle() + " (" + mov.getMovieDetails().getMovieStatus() + ")");
        }
    }

    private Movie getMovieByTitle(String movieTitle) {
        return movies.stream().filter(movie -> movie.getTitle().equalsIgnoreCase(movieTitle)).findFirst().orElse(null);
    }

    private double getOverallRating(Movie movie){
        double total = 0;
        int count = 0;
        for(int i=0; i<movie.getReviews().size(); i++){
            total += movie.getReviews().get(i).getRating();
            count++;
        }
        return (total/count);
    }

    private void updateMovieDetails(int choice,Movie movie) {
        movies.set(choice,movie);
        databaseController.updateMovieDB(movies);
    }

    private int displayAccordingtoMovieStatus(MovieStatus movieStatus) {
        ArrayList<Movie> currentShowing = movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() == movieStatus).collect(Collectors.toCollection(ArrayList::new));
        movieView.displayMovies(currentShowing);
        return currentShowing.size();
    }

    private ArrayList<Movie> getMovieAccordingToStatus(MovieStatus movieStatus) {
        return movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() == movieStatus).collect(Collectors.toCollection(ArrayList::new));
    }


    private void addMovie(Movie movie) {
        movies.add(movie);
    }

}
