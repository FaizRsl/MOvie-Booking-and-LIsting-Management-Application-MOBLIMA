package Controller;

import Model.Movie.Movie;
import Model.Pricing.PriceConfig;
import Model.User.Admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

    public static List<Movie> getMovieFromDB() {
        String filepath = "C:\\Users\\faiz_\\Desktop\\SC2002Assignment\\Moblima\\resources\\MovieDB.dat";
        //return deserializeDataFromDAT("resources/MovieDB.dat");
        return deserializeDataFromDAT(filepath);
    }
    public static void updateMovieDB(List<Movie> movies) {
        String filepath = "C:\\Users\\faiz_\\Desktop\\SC2002Assignment\\Moblima\\resources\\MovieDB.dat";
        //serializingDataFromObject(movies,"resources/MovieDB.dat");
        serializingDataFromObject(movies,filepath);
    }

    public static List<Admin> getAdminFromDB() {
        String filepath = "C:\\Users\\faiz_\\Desktop\\SC2002Assignment\\Moblima\\resources\\AdminDB.dat";
        //return deserializeDataFromDAT("resources/AdminDB.dat");
        return deserializeDataFromDAT(filepath);
    }

    public static void updateAdminFromDB(List<Admin> users) {
        String filepath = "C:\\Users\\faiz_\\Desktop\\SC2002Assignment\\Moblima\\resources\\AdminDB.dat";
        serializingDataFromObject(users,filepath);
    }


    public static void savePriceConfig(PriceConfig priceConfig){
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("database/PriceConfig.txt"));
            output.writeObject(priceConfig);

        } catch (Exception e) {
            System.out.println("Can't save to database. Please try again.");
        }
    }

    public static PriceConfig retrievePriceConfig(){
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("database/PriceConfig.txt"));
            PriceConfig priceConfig = (PriceConfig) input.readObject();
            return priceConfig;

        } catch (FileNotFoundException fileE){
            return (null);
        }

        catch (Exception e) {
            System.out.println("Can't retrieve data from database. Please try again.");
            return (PriceConfig.getInstance());
        }
    }

    private static void serializingDataFromObject(Object obj, String filepath) {
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

    private static List deserializeDataFromDAT(String filepath) {
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
        return (List)obj;
    }

}
