package Model.Movie;

import Model.Pricing.PriceConfig;

import java.io.Serializable;
/**
 * The Enum MovieType, representing types of movie.
 */
public enum MovieType implements Serializable {
	
	/** Normal type. */
    NORMAL,
    
    /** 3D type. */
    THREED,
    
    /** Imax type. */
    IMAX,
    
    /** Blockbuster type. */
    BLOCKBUSTER;
}
