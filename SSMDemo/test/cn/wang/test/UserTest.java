package cn.wang.test;

import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.wang.model.User;
import cn.wang.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserTest {
	@Resource
	private UserService userService;
	
	@Test
	public void testFindUser(){
		User user = userService.findUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void testFindAllUser(){
		List<User> users = userService.findAllUsers();
		System.out.println(users);
	}
	
	@Test
	public void deleteUserById(){
		userService.deleteUserById(10);
	}
	
	@Test
	public void addUser(){
		User user = new User();
		user.setName("free");
		user.setPassword("123");
		user.setEmail("132@qq.com");
		user.setBirthday("1995-04-05");
		userService.addUser(user);
	}
	
}
