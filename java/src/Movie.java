import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Movie implements Serializable{

    private static final long serialVersionUID = 5053775386441033506L;

    private UUID id;
    private String title;
    private String director;
    private String synopsis;
    private String cast;
    private Rating rating;

    private MovieDetails movieDetails;

    public Movie(){
    }

    public Movie(String title, String director, String synopsis, String cast, MovieDetails movieDetails){
        this.id = UUID.randomUUID();
        this.title = title;
        this.director = director;
        this.synopsis = synopsis;
        this.cast = cast;
        this.movieDetails = movieDetails;
    }
    public Movie(String title, String director, String synopsis, String cast, MovieDetails movieDetails, Rating rating){
        this.id = UUID.randomUUID();
        this.title = title;
        this.director = director;
        this.synopsis = synopsis;
        this.cast = cast;
        this.movieDetails = movieDetails;
        this.rating = rating;
    }

    public void getPastReviewsAndRatings(List<Rating> ratingList){

        if(ratingList.size() == 0){
            System.out.println("No reviews and rating.");
            return;
        }

        for(int i = 0; i < ratingList.size(); i++){
            rating = (Rating) ratingList.get(i);
            System.out.print((i+1) + ") " + rating.getRating() + ", " + rating.getReview());
        }
    }

    public int getOverallReview(List<Rating> ratingList){
        int total = 0;
        for(int i = 0; i < ratingList.size(); i++){
            rating = (Rating) ratingList.get(i);
            total += rating.getRating()/(i+1);
        }
        return total;
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

    public MovieDetails getMovieDetails(){
        return movieDetails;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void enterNewMovie(List<Movie> movieList){
        String title;
        String director;
        String synopsis;
        String cast;
        Rating rating;
        Ticket ticket;
        boolean seats;
        int totalSales;

        MovieDetails movieDetails = null;
        String movieRatings, movieType, status;
        Scanner sc = new Scanner(System.in);

        System.out.println("Movie Title: ");
        title = sc.nextLine();
        System.out.println("Director: ");
        director = sc.nextLine();
        System.out.println("Synopsis: ");
        synopsis = sc.nextLine();
        System.out.println("Cast: ");
        cast = sc.nextLine();
        System.out.println("Movie Rating (G, PG, PG13, M16, R): ");
        movieRatings = sc.nextLine();
        System.out.println("Movie Type(Horror, Romance, Action, Blockbuster, Comedy): ");
        movieType = sc.nextLine();
        System.out.println("Movie Status (Coming Soon, Preview, Now Showing, End Of Showing): ");
        status = sc.nextLine();

        movieDetails = new MovieDetails(movieRatings, movieType, status);

        Movie mov = new Movie(title, director, synopsis, cast, movieDetails);

        movieList.add(mov);

    }

}
