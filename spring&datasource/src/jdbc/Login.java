package jdbc;

import java.util.Scanner;

public class Login {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ÇëÊäÈëÕËºÅ£º");
		String name = scanner.nextLine();
		System.out.println("ÇëÊäÈëÃÜÂë");
		String pwd = scanner.nextLine();

		DoLogin doLogin = new DoLogin();
		User user = doLogin.findUser(name, pwd);
		if (user != null) {
			System.out.println("»¶Ó­Äú£º" + user.getName());
		} else {
			System.out.println("ÓÃ»§Ãû»òÃÜÂë´íÎó");
		}
	}
}
