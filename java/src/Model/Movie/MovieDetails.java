package Model.Movie;

import java.io.Serializable;

public class MovieDetails implements Serializable{

    private MovieStatus movieStatus;
    private MovieCensorship movieCensorship;

    private MovieType movieType;

    public MovieDetails(MovieStatus movieStatus, MovieCensorship movieCensorship, MovieType movieType){
        this.movieStatus = movieStatus;
        this.movieCensorship = movieCensorship;
        this.movieType = movieType;
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

    public MovieType getMovieType() { return movieType; }

    public void setMovieType(MovieType movieType) { this.movieType = movieType;}

}
