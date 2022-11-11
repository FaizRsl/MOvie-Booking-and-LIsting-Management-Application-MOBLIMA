package Controller;

import Model.Movie.*;
import view.MovieView;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Class MovieController, responsible for managing and manipulating the MovieController database
 */

public class MovieController {

    private DatabaseController databaseController = DatabaseController.getInstance();
    
    /** List of Movie objects. */
    
    private List<Movie> movies;
    
    /** List of MovieView objects */

    private MovieView movieView;
    
    /**
     * Instantiates a new movie controller.
     */

    public MovieController() {
        movieView = new MovieView();
        movies = databaseController.getMovieFromDB();
    }
    
    /**
     * Gets the movies.
     *
     * @return the movies
     */

    public List<Movie> getMovies() {
        movies = databaseController.getMovieFromDB();
        return movies;
    }
    
    /**
     * Gets the non ended movies.
     *
     * @return the non ended movies, in a list of Movie objects.
     */

    public List<Movie> getCurrentlyAvailableMovies() {
        return Stream.of(getMovieAccordingToStatus(MovieStatus.NOWSHOWING),getMovieAccordingToStatus(MovieStatus.PREVIEW))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
    
    /**
     * Creates the movie.
     *
     * @param br the br
     */

    public void createMovie(Scanner sc) {
        Movie movie = movieView.displayCreateMovie(sc);
        addMovie(movie);
        databaseController.updateMovieDB(movies);
    }
    
    /**
     * Removes the movie by status. Updates movie status and after that, the movie database.
     */

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
        Movie movie = movieView.displayUpdateMovie(sc,update-1, movies);
        if (movie != null)
            updateMovieDetails(update-1,movie);

    }

    public int displayMovieFromMovieList(List<Movie> movieList) {
        movieView.displayMovies(movieList);
        return movieList.size();
    }
    
    /**
     * Display currently showing movie.
     */
    
    public int displayCurrentShowingMovie() {
        return displayAccordingtoMovieStatus(MovieStatus.NOWSHOWING);
    }
    
    /**
     * Display preview movie.
     * 
     * @return an integer
     */
    
    public int displayPreviewMovie() {
        return displayAccordingtoMovieStatus(MovieStatus.PREVIEW);
    }
    
    /**
     * Display coming soon movie.
     */

    public int displayComingSoonMovie() {
        return displayAccordingtoMovieStatus(MovieStatus.COMINGSOON);
    }
    
    /**
     * Display ended movies.
     */

    public int displayEndedMovies() {
        return displayAccordingtoMovieStatus(MovieStatus.ENDED);
    }
    
    /**
     * Display all movies.
     */

    public void displayAllMovie() {
        movieView.displayMovies(movies);
    }
    
    /**
     * Show reviews by movie.
     *
     * @param choice the choice of movie
     */

    public void showReviewsByMovie(int choice){
        ArrayList<Movie> showMovies = getMovieAccordingToStatus(MovieStatus.NOWSHOWING);
        if (choice <= showMovies.size()) {
            movieView.displayReviewsByMovies(showMovies.get(choice-1));
        }
    }
    
    /**
     * Adds the reviews.
     *
     * @param name the name
     * @param rating the rating
     * @param reviewContent the review content
     * @param movieChoice the movie choice
     * @param status the status
     */

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
    
    /**
     * Gets the top five movie rating.
     *
     * Calls {@link MovieView#displayMovieWithReviews(List)}
     */

    public void getTopFiveMovieRating(){
        List<Movie> movieList = movies;
        movieList.sort(Collections.reverseOrder(Comparator.comparing(Movie::getRating)));
        List<Movie> topFive = movieList.subList(0,5);
        movieView.getMovieRating(topFive);
    }
    
    /**
     * Gets the movie by status and index.
     *
     * @param movieStatus the movie status
     * @param movieIndex the movie index
     * @return the movie by status and index
     */

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
    
    /**
     * Show details.
     *
     * @param choice the choice
     */

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
    
    /**
     * Gets the movie by title.
     *
     * @param movieTitle the movie title
     * @return the movie by title
     */

    private Movie getMovieByTitle(String movieTitle) {
        return movies.stream().filter(movie -> movie.getTitle().equalsIgnoreCase(movieTitle)).findFirst().orElse(null);
    }
    
    /**
     * Gets the overall rating.
     *
     * @param movie the movie
     * @return the overall rating
     */

    private double getOverallRating(Movie movie){
        double total = 0;
        int count = 0;
        for(int i=0; i<movie.getReviews().size(); i++){
            total += movie.getReviews().get(i).getRating();
            count++;
        }
        return (total/count);
    }
    
    /**
     * Update movie details.
     * @param choice the choice of movie
     * @param movie the movie
     */

    private void updateMovieDetails(int choice,Movie movie) {
        movies.set(choice,movie);
        databaseController.updateMovieDB(movies);
    }
    
    /**
     * Display according to movie status, called by multiple functions with different movie statuses as inputs.
     *
     * @param movieStatus the choice of movie status
     */

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
