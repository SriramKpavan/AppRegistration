package com.jda.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jda.user.model.Login;
import com.jda.user.model.User;

public class UserDaoImpl implements UserDao{
	
	@Autowired
	  DataSource datasource;
	
	  @Autowired
	  JdbcTemplate jdbcTemplate;

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into LoginInfo values(?,?,?,?,?)";
	    jdbcTemplate.update(sql, new Object[] { user.getName(), user.getMobile(), user.getEmail(), generator(user.getPassword()), "abc" });
	}

	@Override
	public User validateUser(Login login) {
		// TODO Auto-generated method stub
		 String sql = "select * from LoginInfo where USERNAME='" + login.getEmail() +  "'";
		 System.out.println(login.getEmail());
	    List<User> users = jdbcTemplate.query(sql, new UserMapper());

	    System.out.println(users.get(0).toString());
	    return users.size() > 0 ? users.get(0) : null;
	}
	
	public String generator(String password) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		return hashedPassword;

	}

	@Override
	public User validateEmail(String email) {
		// TODO Auto-generated method stub
		String sql = "select * from LoginInfo where USERNAME='" + email + "'" ;
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users.size() > 0 ? users.get(0) : null;
	}

	@Override
	public void saveToken(String token, String email){
		String sql = "update LoginInfo set TOKEN ='" + token + "'where USERNAME = '" + email + "'";
		jdbcTemplate.execute(sql);
	}
	
	@Override
	public User validateToken(String email, String token) {
		// TODO Auto-generated method stub
		String sql = "select * from LoginInfo where USERNAME='" + email + "'and TOKEN ='" + token + "'";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users.size() > 0 ? users.get(0) : null;
	}

	@Override
	public User getUserbyToken(String token) {
		// TODO Auto-generated method stub
		 String sql = "select * from LoginInfo where token='" + token 
		 	    + "'";
		 	    List<User> users = jdbcTemplate.query(sql, new UserMapper());
		 	    return users.size() > 0 ? users.get(0) : null;
	}

	@Override
	public void newPassword(String password, String token) {
		// TODO Auto-generated method stub
		String sql="update LoginInfo set password='"+generator(password) +"'  where token='"+token+"'";
		jdbcTemplate.update(sql);
	}
}

class UserMapper implements RowMapper<User> {
	  public User mapRow(ResultSet rs, int arg1) throws SQLException {
	    User user = new User();
	    user.setPassword(rs.getString("PASSWORD"));
	    user.setName(rs.getString("NAME"));
	    user.setEmail(rs.getString("USERNAME"));
	    user.setMobile(rs.getString("NUMBER"));
	    user.setToken(rs.getString("TOKEN"));
	    return user;
	  }
}
