package spring_tx05anno;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApp {
	@Test
	public void transfer() {
		String xmlPath = "spring_tx05anno/applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		AccountService accountService = (AccountService) applicationContext.getBean("accountService");

		accountService.transfer("rose", "jack", 1000);
	}
}
