package com.wang.service.impl;

import com.wang.dao.UserDao;
import com.wang.dao.impl.UserDaoImpl;
import com.wang.domain.User;
import com.wang.exception.UserExistException;
import com.wang.exception.UsersException;
import com.wang.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public void register(User user) throws Exception {
		userDao.addUser(user);
	}

	@Override
	public User login(User user) {
		return userDao.findUser(user);
	}

	@Override
	public boolean findUserByName(String name) throws UserExistException {
		boolean b = userDao.findUserByName(name);
		if (b) {
			throw new UserExistException("用户名已存在");
		}
		return b;
	}

}
