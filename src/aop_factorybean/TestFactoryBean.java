package aop_factorybean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFactoryBean {
	@Test
	public void test() {
		String xmlPath = "aop_factorybean/applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		UserService userServide = (UserService) applicationContext.getBean("proxyService");
		userServide.addUser();
		userServide.updataUser();
		userServide.deleteUser();
	}
}
