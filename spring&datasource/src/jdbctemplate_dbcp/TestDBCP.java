package jdbctemplate_dbcp;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jdbc.User;

public class TestDBCP {
	@Test
	public void updata() {
		User user = new User();
		user.setId(21);
		user.setName("jdbctemplate_dbcp");
		user.setEmail("jdbctemplate_dbcp@123.com");

		String xmlPath = "jdbctemplate_dbcp/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		userDao.updata(user);
	}
}
