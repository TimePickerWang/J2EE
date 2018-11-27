package com.wang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import com.wang.dao.UserDao;
import com.wang.domain.User;
import com.wang.utils.DBUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public void addUser(User user) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("INSERT INTO users(name,password,email,birthday) VALUES (?, ?, ?, ?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(user.getBirthday());
			ps.setString(4, date);

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, ps, conn);
		}
	}

	@Override
	public User findUser(User user){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User u = null;
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select * from users where name = ? and password = ?");
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			if (rs.next()) {
				u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setBirthday(rs.getDate(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		return u;
	}

	public boolean findUserByName(String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select * from users where name=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		return false;
	}
}
