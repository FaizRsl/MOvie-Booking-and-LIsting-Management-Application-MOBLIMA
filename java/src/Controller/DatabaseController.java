package Controller;

import Model.Pricing.PriceConfig;

import java.io.*;

public class DatabaseController {

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
            System.out.println("Can't retrive data from database. Please try again.");
            return (PriceConfig.getInstance());
        }
    }

}
