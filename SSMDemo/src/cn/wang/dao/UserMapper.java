package cn.wang.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.wang.model.User;

@Repository   
public interface UserMapper{
	
	public User findUserById(Integer id);

	public List<User> findAllUsers();

	public void deleteUserById(Integer id);

	public void addUser(User user);

	public void updataUser(User user);

}
