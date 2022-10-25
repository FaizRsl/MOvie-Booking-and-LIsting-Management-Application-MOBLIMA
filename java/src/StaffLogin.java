
import java.io.IOException;
import java.util.*;


public class StaffLogin {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String username = "";
		String password = "";
		Scanner sc = new Scanner(System.in);
		
		ExcelEditor excel = new ExcelEditor("UserPassword.csv");
		System.out.print("Username: ");
		username = sc.next();
		System.out.print("Password: ");
		password = sc.next();
		
		boolean success = checkLogin(excel,username,password);
		
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
					ArrayList<String> inputList = new ArrayList<String>();
					System.out.println("Enter Username: ");
					inputList.add((String) sc.next());
					System.out.println("Enter Password ");
					inputList.add(sc.next());
					excel.createRow("UserPassword.csv",inputList, 0);
					break;
				default:
					success = false;
					break;
			}
		}
		sc.close();
	}

	public static boolean checkLogin(ExcelEditor excel, String username, String password) throws IOException{

		ArrayList<ArrayList<String>> csvData = excel.getData();
		int skip = 1;
		for(int i = 0; i < csvData.size(); i++) {
			if(skip == 1) {
				skip = 0;
				continue;
			}
			if(csvData.get(i).get(0).equals(username) && csvData.get(i).get(1).equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	
	
}
