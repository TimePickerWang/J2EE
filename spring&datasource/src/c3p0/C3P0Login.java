package c3p0;

import java.util.Scanner;
import jdbc.User;

public class C3P0Login {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("�������˺ţ�");
		String name = scanner.nextLine();
		System.out.println("����������");
		String pwd = scanner.nextLine();

		C3P0DoLogin doLogin = new C3P0DoLogin();
		User user = doLogin.findUser(name, pwd);
		if (user != null) {
			System.out.println("��ӭ����" + user.getName());
		} else {
			System.out.println("�û������������");
		}
	}

}
