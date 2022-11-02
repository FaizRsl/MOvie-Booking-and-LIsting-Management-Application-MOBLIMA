package Model.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Movie implements Serializable{

    private static final long serialVersionUID = 5053775386441033506L;
    private UUID id;
    private String title;
    private String director;
    private String synopsis;
    private double rating = -1.0;
    private ArrayList<Review> reviews;
    private ArrayList<String> casts;
    private MovieDetails movieDetails;

    public Movie(){}
    public Movie(String title, MovieDetails movieDetails){
        this.title = title;
        this.movieDetails = movieDetails;
        this.reviews = new ArrayList<Review>();
        this.casts = new ArrayList<String>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public MovieDetails getMovieDetails(){
        return movieDetails;
    }

    public void setMovieDetails(MovieDetails movieDetails) {this.movieDetails = movieDetails;}

    public double getRating() {return rating;}

    public void setRating(double rating) {this.rating = rating;}

    public ArrayList<Review> getReviews() {return reviews;}

    public void setReviews(ArrayList<Review> reviews) {this.reviews = reviews;}

    public ArrayList<String> getCasts() {return casts;}

    public void setCasts(ArrayList<String> casts) {this.casts = casts;}

}
