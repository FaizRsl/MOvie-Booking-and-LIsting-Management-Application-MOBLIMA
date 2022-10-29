public class MovieDetails {

    enum MovieRating{
        G,
        PG,
        PG13,
        M16,
        R
    }

    enum MovieType {
        HORROR,
        ROMANCE,
        ACTION,
        BLOCKBUSTER,
        COMEDY
    }
    enum Status {
        ComingSoon,
        Preview,
        NowShowing,
        EndOfShowing
    }

    private String movieRating;
    private String movieType;
    private String status;

    public MovieDetails(String movieRating, String movieType, String status){
        this.movieRating = movieRating;
        this.movieType = movieType;
        this.status = status;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
