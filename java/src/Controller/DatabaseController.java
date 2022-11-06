package Controller;

import Model.Booking.Booking;
import Model.Cinema.Cineplex;
import Model.Movie.Movie;
import Model.Pricing.PriceConfig;
import Model.User.Admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

    private static DatabaseController instance = null;

    public static DatabaseController getInstance(){
        if(instance == null)
                instance = new DatabaseController();
        return instance;
    }
    public List<Booking> getBookingFromDB() { return (List)deserializeDataFromDAT("resources/BookingDB.dat"); }

    public void updateBookingDB(List<Booking> bookings) { serializingDataFromObject(bookings,"resources/BookingDB.dat"); }

    public List<Cineplex> getCineplexFromDB() {return (List)deserializeDataFromDAT("resources/CineplexDB.dat"); }
    public void updateCineplexDB(List<Cineplex> cineplexes) { serializingDataFromObject(cineplexes,"resources/CineplexDB.dat");}
    public  List<Movie> getMovieFromDB() {
        return (List<Movie>)deserializeDataFromDAT("resources/MovieDB.dat");
    }
    public void updateMovieDB(List<Movie> movies) {
        serializingDataFromObject(movies,"resources/MovieDB.dat");
    }

    public List<Admin> getAdminFromDB() {
        return (List<Admin>) deserializeDataFromDAT("resources/AdminDB.dat");
    }

    public void updateAdminFromDB(List<Admin> users) { serializingDataFromObject(users,"resources/AdminDB.dat"); }

    public void savePriceConfig(PriceConfig priceConfig) { serializingDataFromObject(priceConfig,"resources/PriceConfig.dat");}

    public PriceConfig retrievePriceConfig() {
        return (PriceConfig) deserializeDataFromDAT("resources/PriceConfig.dat");
    }

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
