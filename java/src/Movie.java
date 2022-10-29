import java.io.Serializable;
import java.util.UUID;

public class Movie implements Serializable{

    private static final long serialVersionUID = 5053775386441033506L;

    private UUID id;
    private String title;
    private String director;
    private String synopsis;
    private String cast;
    private Rating rating;
    private Ticket ticket;

    private MovieDetails movieDetails;
    private boolean seats;

    private int totalSales;

    public Movie(String title, String director, String synopsis, String cast, MovieDetails movieDetails){
        this.id = UUID.randomUUID();
        this.title = title;
        this.director = director;
        this.synopsis = synopsis;
        this.cast = cast;
        this.movieDetails = movieDetails;
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

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public boolean isSeats() {
        return seats;
    }

    public void setSeats(boolean seats) {
        this.seats = seats;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public MovieDetails getMovieDetails(){
        return movieDetails;
    }

    public void getPastReviewsAndReview(){

    }

    public int getOverallReview(){
        return 0;
    }


}
