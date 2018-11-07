package aop_aspect_xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAspectXml {
	@Test
	public void test() {
		String xmlPath = "aop_aspect_xml/applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		
		UserService userServide = (UserService) applicationContext.getBean("userService");
		userServide.addUser();
		userServide.updataUser();
		userServide.deleteUser();
	}
}
