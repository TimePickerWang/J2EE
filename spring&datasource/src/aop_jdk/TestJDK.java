package aop_jdk;

import org.junit.Test;

public class TestJDK {
	@Test
	public void test() {
		UserService userServide = MyBeanFactory.createService();
		userServide.addUser();
		userServide.updataUser();
		userServide.deleteUser();
	}
}
