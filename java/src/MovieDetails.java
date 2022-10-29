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

    private MovieRating movieRating;
    private MovieType movieType;
    private Status status;

    public MovieDetails(){}

    public MovieRating getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(MovieRating movieRating) {
        this.movieRating = movieRating;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
