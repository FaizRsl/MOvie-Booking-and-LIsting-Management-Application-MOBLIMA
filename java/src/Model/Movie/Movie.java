package Model.Movie;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.UUID;

/**
 * The Class Movie.
 */

public class Movie implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5053775386441033506L;

    /** UUID object representing Movie id. */
    private UUID id;

    /** String object title. */
    private String title;

    /** String object director. */
    private String director;

    /** String object synopsis. */
    private String synopsis;

    /** The rating, initialised as -(1.0). */
    private double rating = -1.0;

    /** ArrayList of Review objects. */
    private ArrayList<Review> reviews;

    /** ArrayList of String objects, representing the cast for the Movie object. */
    private ArrayList<String> casts;

    /** MovieDetails object.
     * @see MovieDetails
     * */
    private MovieDetails movieDetails;


    /**
     * Instantiates a new movie.
     *
     * @param title the title
     * @param movieDetails the movie details
     */
    public Movie(String title, MovieDetails movieDetails){
        this.title = title;
        this.movieDetails = movieDetails;
        this.reviews = new ArrayList<Review>();
        this.casts = new ArrayList<String>();
    }

    /**
     * Gets the id.
     *
     * @return the id
     */

    public UUID getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */

    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the director.
     *
     * @return the director
     */

    public String getDirector() {
        return director;
    }

    /**
     * Sets the director.
     *
     * @param director the new director
     */

    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Gets the synopsis.
     *
     * @return the synopsis
     */

    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Sets the synopsis.
     *
     * @param synopsis the new synopsis
     */

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Gets the movie details.
     *
     * @return the movie details
     */

    public MovieDetails getMovieDetails(){
        return movieDetails;
    }

    /**
     * Sets the movie details.
     *
     * @param movieDetails the new movie details
     */

    public void setMovieDetails(MovieDetails movieDetails) {this.movieDetails = movieDetails;}

    /**
     * Gets the rating.
     *
     * @return the rating
     */

    public double getRating() {return rating;}

    /**
     * Sets the rating.
     *
     * @param rating the new rating
     */

    public void setRating(double rating) {this.rating = rating;}

    /**
     * Gets the reviews.
     *
     * @return the reviews
     */

    public ArrayList<Review> getReviews() {return reviews;}

    /**
     * Sets the reviews.
     *
     * @param reviews the new reviews
     */

    public void setReviews(ArrayList<Review> reviews) {this.reviews = reviews;}

    /**
     * Gets the movie cast.
     *
     * @return the casts
     */

    public ArrayList<String> getCasts() {return casts;}

    /**
     * Sets the movie cast.
     *
     * @param casts, ArrayList of String objects representing the new cast
     */

    public void setCasts(ArrayList<String> casts) {this.casts = casts;}

    /**
     * To string.
     *
     * @return properly formatted data of Movie object
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Title: %s \n",this.title));
        sb.append("=============================================== \n");
        sb.append(String.format("Director: %s \n",this.director));
        sb.append(String.format("Cast: %s \n",this.casts.toString().substring(1,this.casts.toString().length()-1)));
        sb.append(String.format("Rating: %.1f \n",this.rating));
        sb.append(String.format("Age Rating: %s \n",this.movieDetails.getMovieCensorship()));
        sb.append(String.format("Genre: %s \n",this.movieDetails.getGenre()));
        sb.append(String.format("Synopsis: %s \n",this.synopsis));
        sb.append(String.format("Movie Status: %s \n",this.movieDetails.getMovieStatus().getMovieStatusName()));
        return sb.toString();
    }
}
