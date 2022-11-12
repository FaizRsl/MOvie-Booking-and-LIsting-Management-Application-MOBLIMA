package Model.Movie;

import java.io.Serializable;

/**
 * The Enum MovieStatus.
 */
public enum MovieStatus implements Serializable {
	
	/** Movie status: Coming Soon. */
    COMINGSOON("Coming Soon"),
    
    /** Movie status: Is Currently In Preview. */
    
    PREVIEW("Preview"),
    /** Movie Status: Movie showing has ended. */
    
    ENDED("Ended"),
    /** Movie Status: It is now showing and available for booking. */
    
    NOWSHOWING("Now Showing");
	/** The movie status name. */
    private String movieStatusName;
    /**
     * Instantiates a new movie status.
     *
     * @param movieStatusName the movie status name
     */
    MovieStatus(String movieStatusName){this.movieStatusName = movieStatusName;}
    
    /**
     * Gets the movie status name.
     *
     * @return the movie status name
     */
    public String getMovieStatusName(){return movieStatusName;}
}
