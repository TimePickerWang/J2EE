package aop_cglib;

import org.junit.Test;

public class TestCglib {
	@Test
	public void test() {
		UserServiceImpl userServide = MyBeanFactory.createService();
		userServide.addUser();
		userServide.updataUser();
		userServide.deleteUser();
	}
}
