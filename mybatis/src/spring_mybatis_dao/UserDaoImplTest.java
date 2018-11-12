package spring_mybatis_dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoImplTest {
	private ApplicationContext applicationContext;
	
	@Before
	public void setUp(){
		applicationContext = new ClassPathXmlApplicationContext("spring_mybatis_dao/applicationContext.xml");
	}
	
	
	@Test
	public void test() throws Exception {
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		User user = userDao.findUserById(1);
		System.out.println(user);
	}

}
