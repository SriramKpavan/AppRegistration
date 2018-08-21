package com.jda.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.jda.user.dao.UserDao;
import com.jda.user.model.Login;
import com.jda.user.model.User;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public void register(User user) {
		// TODO Auto-generated method stub
		userDao.register(user);
	}

	@Override
	@Transactional
	public User validateUser(Login login) {
		// TODO Auto-generated method stub
		
		User user = userDao.validateUser(login);
		return user;
	}

	public String generator(String password) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		return hashedPassword;

	}
	
}
