package Controller;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SerializerController {

    public static void serializingDataFromObject(Object obj, String filepath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filepath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Unable to Retrieve File, Ensure that your file is placed in the right directory!");
            throw new RuntimeException(fileNotFoundException);
        } catch (IOException e) {
            System.out.println("Unable to write or read the File!");
            throw new RuntimeException(e);
        }
    }

    public static ArrayList deserializeDataFromDAT(String filepath) {
        Object obj = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filepath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            obj = objectInputStream.readObject();
            objectInputStream.close();
        } catch(IOException e) {
            System.out.println("Unable to write or read the File!");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Serialization of File has issues, re-serialize the file again");
            throw new RuntimeException(e);
        }
        return (ArrayList)obj;
    }

}
