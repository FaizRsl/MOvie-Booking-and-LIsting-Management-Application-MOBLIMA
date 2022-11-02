package Model.Movie;

import java.io.Serializable;

public class MovieDetails implements Serializable{

    private MovieStatus movieStatus;
    private MovieCensorship movieCensorship;

    private Genre genre;

    public MovieDetails(MovieStatus movieStatus, MovieCensorship movieCensorship,Genre genre){
        this.movieStatus = movieStatus;
        this.movieCensorship = movieCensorship;
        this.genre = genre;
    }

    public MovieStatus getMovieStatus() {
        return movieStatus;
    }

    public void setMovieStatus(MovieStatus movieStatus) {
        this.movieStatus = movieStatus;
    }

    public MovieCensorship getMovieCensorship() {
        return movieCensorship;
    }

    public void setMovieCensorship(MovieCensorship movieCensorship) {
        this.movieCensorship = movieCensorship;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
