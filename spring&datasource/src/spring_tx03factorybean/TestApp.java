package spring_tx03factorybean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestApp {
	@Test
	public void transfer() {
		String xmlPath = "spring_tx03factorybean/applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		AccountService accountService = (AccountService) applicationContext.getBean("proxyAccountService");

		accountService.transfer("rose", "jack", 1000);
	}
}
