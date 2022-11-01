package Controller;

import Model.Movie.Movie;
import Model.User.SerializeDB;
import view.MovieView;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class MovieController {

    private ArrayList<Movie> movies;

    private MovieView movieView;

    private BufferedReader br;

    public MovieController(BufferedReader br) {
        this.br = br;
        movieView = new MovieView();
    }

    public ArrayList<Movie> serializeDataFromDAT() {
        SerializerController.deserializeDataFromDAT("resources/MoviesDB.dat");
        return movies;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void findMovieByTitle(String movieTitle) {
        Movie tmp = movies.stream().filter(movie -> movie.getTitle().equalsIgnoreCase(movieTitle)).findFirst().orElse(null);
        movieView.findMovieByTitle(tmp);
    }

    public void displayMovie() throws IOException {
        movieView.displayAllMovies(movies);
        int choice = Integer.parseInt(br.readLine());
        if (choice > movies.size()) {
            Movie movie = movies.get(choice-1);
            movieView.displayMovieDetails(choice,movie);
        }
    }
}
