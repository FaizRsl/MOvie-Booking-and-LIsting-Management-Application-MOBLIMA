package Controller;

import Model.User.Admin;

import java.io.File;
import java.util.List;

public class AdminController {

    private final DatabaseController databaseController = DatabaseController.getInstance();

    public AdminController(){
    }

    public void addAdmin(Admin admin) {
        List<Admin> adminList = databaseController.getAdminFromDB();
        adminList.add(admin);
        databaseController.updateAdminFromDB(adminList);
    }

    public boolean authorizeAdmin(String username, String password){
        List<Admin> adminList = databaseController.getAdminFromDB();
        for(int i = 0; i < adminList.size(); i++) {
            Admin user = adminList.get(i);
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
