package Model.Movie;

import java.io.Serializable;

public class MovieDetails implements Serializable{

    private MovieStatus movieStatus;
    private MovieCensorship movieCensorship;

    public MovieDetails(MovieStatus movieStatus, MovieCensorship movieCensorship){
        this.movieStatus = movieStatus;
        this.movieCensorship = movieCensorship;
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

}
