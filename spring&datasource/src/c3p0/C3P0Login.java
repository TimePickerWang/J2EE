package c3p0;

import java.util.Scanner;
import jdbc.User;

public class C3P0Login {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ÇëÊäÈëÕËºÅ£º");
		String name = scanner.nextLine();
		System.out.println("ÇëÊäÈëÃÜÂë");
		String pwd = scanner.nextLine();

		C3P0DoLogin doLogin = new C3P0DoLogin();
		User user = doLogin.findUser(name, pwd);
		if (user != null) {
			System.out.println("»¶Ó­Äú£º" + user.getName());
		} else {
			System.out.println("ÓÃ»§Ãû»òÃÜÂë´íÎó");
		}
	}

}
