package jdbctemplate_properties;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import jdbc.User;

public class TestProps {
	@Test
	public void updata() {
		User user = new User();
		user.setId(21);
		user.setName("jdbctemplate_prop");
		user.setEmail("jdbctemplate_prop@123.cn");

		String xmlPath = "jdbctemplate_properties/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");

		userDao.updata(user);
	}
}
