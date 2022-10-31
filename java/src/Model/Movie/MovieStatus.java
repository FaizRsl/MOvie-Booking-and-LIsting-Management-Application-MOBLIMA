package Model.Movie;

import java.io.Serializable;

public enum MovieStatus implements Serializable {

    COMINGSOON("Coming Soon"),
    PREVIEW("Preview"),
    ENDED("Ended"),
    NOWSHOWING("Now Showing");
    private String movieStatusName;

    MovieStatus(String movieStatusName){this.movieStatusName = movieStatusName;}

    public String getMovieStatusName(){return movieStatusName;}
}
