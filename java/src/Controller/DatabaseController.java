package Controller;

import Model.Booking.Booking;
import Model.Cinema.Cineplex;
import Model.Movie.Movie;
import Model.Pricing.PriceConfig;
import Model.User.Admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class DatabaseController.
 */

public class DatabaseController {

    private static DatabaseController instance = null;

    public static DatabaseController getInstance(){
        if(instance == null)
                instance = new DatabaseController();
        return instance;
    }
    
    /**
     * Gets the booking from DB.
     *
     * @return the booking from DB
     */
    
    public List<Booking> getBookingFromDB() { return (List)deserializeDataFromDAT("resources/BookingDB.dat"); }
    
    /**
     * Update booking DB.
     *
     * @param bookings the bookings
     */

    public void updateBookingDB(List<Booking> bookings) { serializingDataFromObject(bookings,"resources/BookingDB.dat"); }
    
    /**
     * Gets the cineplex from DB.
     *
     * @return the cineplex from DB
     */

    public List<Cineplex> getCineplexFromDB() {return (List)deserializeDataFromDAT("resources/CineplexDB.dat"); }
    public void updateCineplexDB(List<Cineplex> cineplexes) { serializingDataFromObject(cineplexes,"resources/CineplexDB.dat");}
    public  List<Movie> getMovieFromDB() {
        return (List<Movie>)deserializeDataFromDAT("resources/MovieDB.dat");
    }
    
    /**
     * Update movie DB.
     *
     * @param list of Movie objects
     */
    
    public void updateMovieDB(List<Movie> movies) {
        serializingDataFromObject(movies,"resources/MovieDB.dat");
    }
    
    /**
     * Gets the admin from DB.
     *
     * @return list of Admin objects
     */

    public List<Admin> getAdminFromDB() {
        return (List<Admin>) deserializeDataFromDAT("resources/AdminDB.dat");
    }
    
    /**
     * Update admin from DB.
     *
     * @param users List of Admin objects, representing our current list of admins
     */

    public void updateAdminFromDB(List<Admin> users) { serializingDataFromObject(users,"resources/AdminDB.dat"); }
    
    /**
     * Save price config.
     *
     * @param priceConfig the price config
     */

    public void savePriceConfig(PriceConfig priceConfig) { serializingDataFromObject(priceConfig,"resources/PriceConfig.dat");}
    
    /**
     * Retrieve price config.
     *
     * @return object of type PriceConfig
     * @see Pricing#PriceConfig
     */

    public PriceConfig retrievePriceConfig() {
        return (PriceConfig) deserializeDataFromDAT("resources/PriceConfig.dat");
    }
    
    /**
     * Serializing data from object.
     *
     * @param obj object of type Object
     * @param filepath object of type String
     */

    private void serializingDataFromObject(Object obj, String filepath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filepath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Unable to Retrieve File, Ensure that your file is placed in the right directory!");
            System.out.println(fileNotFoundException.getMessage());
        } catch (IOException e) {
            System.out.println("Unable to write or read the File!");
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Deserialize data from DAT.
     *
     * @param filepath the filepath
     * @return the object
     */

    private Object deserializeDataFromDAT(String filepath) {
        Object obj = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filepath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            obj = objectInputStream.readObject();
            objectInputStream.close();
        } catch(IOException e) {
            System.out.println("Unable to write or read the File!");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Serialization of File has issues, re-serialize the file again");
            System.out.println(e.getMessage());
        }
        return obj;
    }

}
