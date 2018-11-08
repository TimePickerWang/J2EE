package jdbctemplate_daosupport;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import jdbc.User;

public class TestDbcpDaoSupport {

	@Test
	public void update() {
		User user = new User();
		user.setId(21);
		user.setName("jdbctemplate_daoSupport");
		user.setEmail("jdbctemplate_daoSupport@123.cn");
		
		String xmlPath = "jdbctemplate_daosupport/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

		// 获得目标类
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		userDao.updata(user);
	}
}
