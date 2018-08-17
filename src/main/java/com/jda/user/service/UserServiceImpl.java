package com.jda.user.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.jda.user.dao.UserDao;
import com.jda.user.model.Login;
import com.jda.user.model.User;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		userDao.register(user);
	}

	@Override
	public User validateUser(Login login) {
		// TODO Auto-generated method stub
		
		User user = userDao.validateUser(login);
		return user;
	}

}
