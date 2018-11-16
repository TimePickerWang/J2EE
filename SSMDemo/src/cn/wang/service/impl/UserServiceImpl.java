package cn.wang.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.wang.dao.UserMapper;
import cn.wang.model.User;
import cn.wang.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	public User findUserById(Integer id) {
		User user = userMapper.findUserById(id);
		return user;
	}

	public List<User> findAllUsers() {
		List<User> list = userMapper.findAllUsers();
		return list;
	}

	public void deleteUserById(Integer id) {
		userMapper.deleteUserById(id);	
	}

	public void addUser(User user) {
		userMapper.addUser(user);
	}

	public void updataUser(User user) {
		userMapper.updataUser(user);
	}
}
