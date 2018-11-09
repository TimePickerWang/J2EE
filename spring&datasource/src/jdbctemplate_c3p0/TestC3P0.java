package jdbctemplate_c3p0;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import jdbc.User;

public class TestC3P0 {
	@Test
	public void updata() {
		User user = new User();
		user.setId(21);
		user.setName("jdbctemplate_c3p0");
		user.setEmail("jdbctemplate_c3p0@123.cn");

		String xmlPath = "jdbctemplate_c3p0/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		userDao.updata(user);
	}
}
