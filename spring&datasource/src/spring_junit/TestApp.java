package spring_junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ��junit��������
 * @author PC
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring_junit/applicationContext.xml")
public class TestApp {
	@Autowired  //��junit���ϣ�����Ҫ��spring xml����ɨ��
	private AccountService accountService;
	
	@Test
	public void transfer() {
//		String xmlPath = "spring_junit/applicationContext.xml";
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
//		AccountService accountService = (AccountService) applicationContext.getBean("accountService");

		accountService.transfer("rose", "jack", 1000);
	}
}
