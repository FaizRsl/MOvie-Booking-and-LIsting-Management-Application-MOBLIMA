package Model.Cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Class Cineplex.
 */

public class Cineplex implements Serializable {
	
	/** String representing cineplex name. */
	
    private String cineplexName;
    
    /** ArrayList cinemas containing objects of class Cinema. */

    private ArrayList<Cinema> cinemas;
    
    /**
     * Instantiates a new cineplex.
     *
     * @param cineplexName the cineplex name
     * @param cinemaList the cinema list
     */

    public Cineplex(String cineplexName, ArrayList<Cinema> cinemaList){
        this.cineplexName = cineplexName;
        this.cinemas = cinemaList;
    }
    
    /**
     * Gets the cineplex name.
     *
     * @return String of cineplex name
     */

    public String getCineplexName() {
        return cineplexName;
    }
    
    /**
     * Sets the cineplex name.
     *
     * @param String cineplexName the new cineplex name
     */

    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }
    
    /**
     * Gets the cinemas.
     *
     * @return ArrayList of Cinema objects.
     */

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }
    
    /**
     * Adds the cinema to instance variable cinemas
     *
     * @param cinema object Cinema.
     */

    public void addCinema(Cinema cinema){
        this.cinemas.add(cinema);
    }

}
