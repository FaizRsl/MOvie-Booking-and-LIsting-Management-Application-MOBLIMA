package Controller;

import Model.User.Admin;

import java.io.File;
import java.util.List;

public class AdminController {

    public AdminController(){
    }

    public static void addAdmin(Admin admin) {
        List<Admin> adminList = DatabaseController.getAdminFromDB();
        adminList.add(admin);
        DatabaseController.updateAdminFromDB(adminList);
    }

    public static boolean authorizeAdmin(String username, String password){
        List<Admin> adminList = DatabaseController.getAdminFromDB();
        System.out.println(new File("").getAbsolutePath());
        try {
            for(int i = 0; i < adminList.size(); i++) {
                Admin user = adminList.get(i);
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
