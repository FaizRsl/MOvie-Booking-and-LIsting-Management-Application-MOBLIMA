
import java.io.IOException;
import java.util.*;


public class StaffLogin {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String username = "";
		String password = "";
		Scanner sc = new Scanner(System.in);

		ExcelEditor excel = new ExcelEditor("UserPassword.xlsx");
		
		Map<Integer,ArrayList<String>> data = excel.getData(0);
		System.out.print("Username: ");
		username = sc.next();
		System.out.print("Password: ");
		password = sc.next();
		
		boolean success = checkLogin(data, username,password);
		
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
					excel.createRow(inputList, 0);
					break;
				default:
					success = false;
					break;
			}
		}
		
	}

	public static boolean checkLogin(Map<Integer, ArrayList<String>> data, String username, String password) throws IOException{
		
		
		int skip = 1; //skip first row
		for(Map.Entry<Integer,ArrayList<String>> set : data.entrySet()) {
			if(skip == 1) {
				skip = 0;
				continue;
			}
			ArrayList<String> list = set.getValue();
			if(!(list.get(0).equals(username) && list.get(1).equals(password))) continue;
			return true;
		}

		return false;
	}
	
	
	
}
