package com.wang.service;

import com.wang.domain.User;
import com.wang.exception.UserExistException;

public interface UserService {
	public void register(User user) throws Exception;

	public User login(User user);

	public boolean findUserByName(String name) throws UserExistException;
}
