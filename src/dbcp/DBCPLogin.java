package dbcp;

import java.util.Scanner;
import jdbc.User;

public class DBCPLogin {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ÇëÊäÈëÕËºÅ£º");
		String name = scanner.nextLine();
		System.out.println("ÇëÊäÈëÃÜÂë");
		String pwd = scanner.nextLine();

		DBCPDoLogin doLogin = new DBCPDoLogin();
		User user = doLogin.findUser(name, pwd);
		if (user != null) {
			System.out.println("»¶Ó­Äú£º" + user.getName());
		} else {
			System.out.println("ÓÃ»§Ãû»òÃÜÂë´íÎó");
		}
	}
}
