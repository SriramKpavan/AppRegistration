package com.jda.user.dao;

import com.jda.user.model.Login;
import com.jda.user.model.User;

public interface UserDao {

	void register(User user);
	 User validateUser(Login login);
	User validateEmail(String email);
	User validateToken(String email, String token);
	void saveToken(String token, String email);
	User getUserbyToken(String resetToken);
	void newPassword(String password, String token);
}
