package com.jda.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
		String sql = "insert into LoginInfo values(?,?,?,?)";
	    jdbcTemplate.update(sql, new Object[] { user.getEmail(), user.getPassword(), user.getName(), user.getMobile() });
	}

	@Override
	public User validateUser(Login login) {
		// TODO Auto-generated method stub
		 String sql = "select * from LoginInfo where USERNAME='" + login.getEmail() + "' and PASSWORD='" + login.getPassword()
	    + "'";
	    List<User> users = jdbcTemplate.query(sql, new UserMapper());
	    return users.size() > 0 ? users.get(0) : null;
	}

}

class UserMapper implements RowMapper<User> {
	  public User mapRow(ResultSet rs, int arg1) throws SQLException {
	    User user = new User();
	    user.setPassword(rs.getString("PASSWORD"));
	    user.setName(rs.getString("NAME"));
	    user.setEmail(rs.getString("USERNAME"));
	    user.setMobile(rs.getString("NUMBER"));
	    return user;
	  }
}
