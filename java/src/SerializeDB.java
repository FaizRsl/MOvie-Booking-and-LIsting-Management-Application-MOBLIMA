import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializeDB {

    private String filename;
    private List details = null;
    private boolean fileExists = true;
    public SerializeDB(){ //New Database
        this.filename = "";
    }

    public SerializeDB(String filename) {
        this.filename = filename;
        FileInputStream fis = null;
        ObjectInputStream in = null;

        File f = new File(filename);
        if(f.exists()){
            try {
                fis = new FileInputStream(filename);
                in = new ObjectInputStream(fis);
                details = (ArrayList) in.readObject();
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();

            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } else {
            this.filename = filename;
            fileExists = false;
        }

            
    }

    public List getObjectsList() {
        return details;
    }

    public String getFilename(){
        return filename;
    }
    
    public boolean getFileExists(){
        return fileExists;
    }

    public void writeToDB(List list) {
        if(fileExists != true){
            writeToDB(list, filename);
        }
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeToDB(List list, String filename) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
