package cn.wang.service;

import java.util.List;

import cn.wang.model.User;

public interface UserService {
	
	public User findUserById(Integer id);

	public List<User> findAllUsers();

	public void deleteUserById(Integer id);

	public void addUser(User user);

	public void updataUser(User user);
	
}
