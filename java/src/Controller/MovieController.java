package Controller;

import Model.Movie.*;
import view.MovieView;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MovieController {

    private List<Movie> movies;

    private MovieView movieView;

    public MovieController() {
        movieView = new MovieView();
        movies = DatabaseController.getMovieFromDB();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void createMovie(BufferedReader br) {
        String title;
        String director;
        String synopsis;
        double rating;
        ArrayList<Review> reviews = new ArrayList<Review>();
        ArrayList<String> casts = new ArrayList<String>();
        MovieDetails movieDetails;
        MovieStatus movieStatus = null;
        MovieCensorship movieCensorship = null;
        Genre genre = null;

        Scanner sc = new Scanner(System.in);


        System.out.println("Movie Title: ");
        title = sc.nextLine();
        System.out.println("Director: ");
        director = sc.nextLine();
        System.out.println("Synopsis: ");
        synopsis = sc.nextLine();

        System.out.println("Cast Members: (eg: robert, alex, sam) -> use , to add more cast members");
        String cast = sc.nextLine();
        List<String> tempList = Arrays.asList(cast.split(","));
        for (int i = 0; i < tempList.size(); i++){
            casts.add(i,tempList.get(i).trim());
        }
        System.out.println("Rating: ");
        rating = sc.nextDouble();

        System.out.println("Movie Status: ");
        System.out.println("1) Coming Soon");
        System.out.println("2) Preview");
        System.out.println("3) Now Showing");
        System.out.println("4) Ended");
        int choice;
        boolean loop = true;
        while(loop){

            choice = sc.nextInt();
            if(choice > 4 && choice < 0){
                System.out.println("Please input a valid number!");
                continue;
            }
            switch(choice){
                case 1:
                    movieStatus = MovieStatus.COMINGSOON;
                    break;
                case 2:
                    movieStatus = MovieStatus.PREVIEW;
                    break;
                case 3:
                    movieStatus = MovieStatus.NOWSHOWING;
                    break;
                case 4:
                    movieStatus = MovieStatus.ENDED;
                    break;
                default:
            }
            loop = false;
        }

        System.out.println("Movie Censorship: ");
        System.out.println("1) G");
        System.out.println("2) PG");
        System.out.println("3) PG13");
        System.out.println("4) NC16");
        System.out.println("5) M18");
        System.out.println("6) R21");


        loop = true;
        while(loop){

            choice = sc.nextInt();
            if(choice > 6 && choice < 0){
                System.out.println("Please input a valid number!");
                continue;
            }
            switch(choice){
                case 1:
                    movieCensorship = MovieCensorship.G;
                    break;
                case 2:
                    movieCensorship = MovieCensorship.PG;
                    break;
                case 3:
                    movieCensorship = MovieCensorship.PG13;
                    break;
                case 4:
                    movieCensorship = MovieCensorship.NC16;
                    break;
                case 5:
                    movieCensorship = MovieCensorship.M18;
                    break;
                case 6:
                    movieCensorship = MovieCensorship.R21;
                    break;
                default:
            }
            loop = false;
        }

        System.out.println("Genre: ");
        System.out.println("1) Action");
        System.out.println("2) Romance");
        System.out.println("3) Horror");
        System.out.println("4) Adventure");
        System.out.println("5) Mystery");
        loop = true;

        while(loop){

            choice = sc.nextInt();
            if(choice > 6 && choice < 0){
                System.out.println("Please input a valid number!");
                continue;
            }
            switch(choice){
                case 1:
                    genre = Genre.ACTION;
                    break;
                case 2:
                    genre = Genre.ROMANCE;
                    break;
                case 3:
                    genre = Genre.HORROR;
                    break;
                case 4:
                    genre = Genre.ADVENTURE;
                    break;
                case 5:
                    genre = Genre.MYSTERY;
                    break;
                default:
                    break;
            }
            loop = false;
        }

        movieDetails = new MovieDetails(movieStatus,movieCensorship,genre);

        Movie mov = new Movie(title, movieDetails);
        mov.setRating(rating);
        mov.setDirector(director);
        mov.setSynopsis(synopsis);
        mov.setReviews(reviews);
        mov.setCasts(casts);

        addMovie(mov);
        DatabaseController.updateMovieDB(movies);
    }

    public void removeMovieByTitle(String movieTitle) {
        Movie movie = getMovieByTitle(movieTitle);
        removeMovie(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
        DatabaseController.updateMovieDB(movies);
    }

    public void updateMovieDetails(Movie movie) {
        movies.remove(movie);
        movies.add(movie);
        DatabaseController.updateMovieDB(movies);
    }

    public Movie getMovieByTitle(String movieTitle) {
        return movies.stream().filter(movie -> movie.getTitle().equalsIgnoreCase(movieTitle)).findFirst().orElse(null);
    }

    public void findMovieByTitle(String movieTitle) {
        Movie movie = getMovieByTitle(movieTitle);
        movieView.findMovieByTitle(movie);
    }
    public void displayCurrentShowingMovie() {
        displayAccordingtoMovieStatus(MovieStatus.NOWSHOWING);
    }
    public void displayPreviewMovie() {
        displayAccordingtoMovieStatus(MovieStatus.PREVIEW);
    }

    public void displayComingSoonMovie() {
        displayAccordingtoMovieStatus(MovieStatus.COMINGSOON);
    }

    public void displayEndedMovies() {
        displayAccordingtoMovieStatus(MovieStatus.ENDED);
    }

    public void displayAllMovie() {
        //ArrayList<Movie> showingMovies = getNonEndedMovies();
        movieView.displayMovies(movies);
    }

    public void showDetails(int choice) {
        //ArrayList<Movie> showMovies = getNonEndedMovies();
        if (choice <= movies.size()) {
         movieView.displayMovieDetails(choice,movies.get(choice-1));
        }
    }

    public void showDetailsCurrentShowing(int choice) {
        ArrayList<Movie> showMovies = getCurrentShowing();
        if (choice <= showMovies.size()) {
            movieView.displayMovieDetails(choice,showMovies.get(choice-1));
        }
    }

    public void showDetailsPreviewShowing(int choice) {
        ArrayList<Movie> showMovies = getPreviewShowing();
        if (choice <= showMovies.size()) {
            movieView.displayMovieDetails(choice,showMovies.get(choice-1));
        }
    }

    private void displayAccordingtoMovieStatus(MovieStatus movieStatus) {
        ArrayList<Movie> currentShowing = movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() == movieStatus).collect(Collectors.toCollection(ArrayList::new));
        movieView.displayMovies(currentShowing);
    }

    private ArrayList<Movie> getNonEndedMovies() {
        return movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() != MovieStatus.ENDED).collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Movie> getCurrentShowing() {
        return movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() == MovieStatus.NOWSHOWING).collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Movie> getPreviewShowing() {
        return movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() == MovieStatus.PREVIEW).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Movie> getMoviesFromDB() {
        movies = DatabaseController.getMovieFromDB();
        return movies;
    }

    private void addMovie(Movie movie) {
        movies.add(movie);
    }
}
