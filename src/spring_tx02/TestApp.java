package spring_tx02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 手动事物管理
 * @author PC
 */

public class TestApp {
	@Test
	public void transfer() {
		String xmlPath = "spring_tx02/applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		AccountService accountService = (AccountService) applicationContext.getBean("accountService");

		accountService.transfer("rose", "jack", 1000);
	}
}
