
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;


public class StaffLogin {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String username = "";
		String password = "";
		Scanner sc = new Scanner(System.in);
		
		SerializeDB userDB = new SerializeDB("UserDB.dat");
		System.out.print("Username: ");
		username = sc.next();
		System.out.print("Password: ");
		password = sc.next();
		
		List list = userDB.getObjectsList();
		boolean success = checkLogin(list, username,password);
		
		if(success) {
			System.out.println("Login Successful");
		} else {
			System.out.println("Login Failed!");
		}

		while(success) {
			System.out.println("1) Create New User (Testing creating row feature): ");
			System.out.println("2) Quit");
			System.out.println("Next Choice: ");
			int input = sc.nextInt();
			switch(input) {
			
				case 1:
					System.out.println("Enter Username: ");
					username = sc.next();
					System.out.println("Enter Password ");
					password = sc.next();
					User user = new User(username,password);
					list.add(user);
					userDB.writeToDB(list);
					break;
				default:
					success = false;
					break;
			}
		}
	}

	public static Boolean checkLogin(List list,String username, String password) {
		
		
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
