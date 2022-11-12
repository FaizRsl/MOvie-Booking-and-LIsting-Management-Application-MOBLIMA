package Model.Movie;

import java.io.Serializable;

/**
 * The Class MovieDetails.
 */
public class MovieDetails implements Serializable{
	
    /** The movie status, object of type MovieStatus.
     * @see MovieStatus */
    private MovieStatus movieStatus;
    
    /** The movie censorship, object of type MovieCensorship.
     * @see MovieCensorship */
    private MovieCensorship movieCensorship;
    
    /** The genre, object of type Genre.
     * @see Genre */
    private Genre genre;

    /**
     * Instantiates a new MovieDetails object.
     *
     * @param movieStatus the movie status
     * @param movieCensorship the movie censorship
     * @param genre the genre]
     * 
     */
    public MovieDetails(MovieStatus movieStatus, MovieCensorship movieCensorship,Genre genre){
        this.movieStatus = movieStatus;
        this.movieCensorship = movieCensorship;
        this.genre = genre;
    }
    
    /**
     * Gets the movie status.
     *
     * @return the movie status
     */
    public MovieStatus getMovieStatus() {
        return movieStatus;
    }
    /**
     * Sets the movie status.
     *
     * @param movieStatus the new movie status
     */
    public void setMovieStatus(MovieStatus movieStatus) {
        this.movieStatus = movieStatus;
    }
    /**
     * Gets the movie censorship.
     *
     * @return the movie censorship
     */
    public MovieCensorship getMovieCensorship() {
        return movieCensorship;
    }
    /**
     * Sets the movie censorship.
     *
     * @param movieCensorship the new movie censorship
     */
    public void setMovieCensorship(MovieCensorship movieCensorship) {
        this.movieCensorship = movieCensorship;
    }
    /**
     * Gets the genre.
     *
     * @return the genre
     */
    public Genre getGenre() {
        return genre;
    }
    /* Sets the genre.
    *
    * @param genre the new genre
    */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
