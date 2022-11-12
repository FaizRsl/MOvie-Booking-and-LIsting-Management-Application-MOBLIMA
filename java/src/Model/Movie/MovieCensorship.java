package Model.Movie;

import java.io.Serializable;

/**
 * The Enum MovieCensorship, representing all levels of age restrictions for movies
 */

public enum MovieCensorship implements Serializable {
	
	/** Appropriate for people of all ages. */
    G,
    
    /** Parental Guidance suggested for young children. */
    PG,
    
    /** Parental Guidance required for those below 13. */
    PG13,
    
    /** For 16 and above only. */
    NC16,
    
    /** For 18 and above only. */
    M18,
    
    /** 21 and above only. */
    R21;
}
