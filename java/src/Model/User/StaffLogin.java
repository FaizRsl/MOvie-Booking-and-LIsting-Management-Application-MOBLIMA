package Model.User;

import Controller.SerializerController;

import java.util.List;
public class StaffLogin {

    public StaffLogin(){

    }

    public boolean login(String username, String password){
        List<Admin> list = SerializerController.deserializeDataFromDAT("resources/UserDB.dat");
        boolean success = checkLogin(list, username,password);
        return success;
    }

    public static Boolean checkLogin(List<Admin> list, String username, String password) {
        try {
            for(int i = 0; i < list.size(); i++) {
                Admin user = (Admin)list.get(i);
                if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println( "Exception >> " + e.getMessage());
        }
        return false;
    }
}
