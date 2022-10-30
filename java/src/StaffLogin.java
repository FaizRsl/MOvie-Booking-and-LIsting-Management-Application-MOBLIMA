import java.util.List;
public class StaffLogin {

    public StaffLogin(){

    }

    public boolean login(String username, String password){
        SerializeDB userDB = new SerializeDB("UserDB.dat");
        List<User> list = userDB.getObjectsList();
        boolean success = checkLogin(list, username,password);
        return success;
    }

    public static Boolean checkLogin(List<User> list,String username, String password) {
        try {
            for(int i = 0; i < list.size(); i++) {
                User user = (User)list.get(i);
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
