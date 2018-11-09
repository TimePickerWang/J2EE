package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

public class DoLogin {
	public User findUser(String name, String pwd) {
		Connection conn = null;
		// Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "SELECT * FROM users WHERE NAME=? AND PASSWORD=?";
			ps = conn.prepareStatement(sql);
			// ¸ø?¸³Öµ
			ps.setString(1, name);
			ps.setString(2, pwd);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setBirthday(rs.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		return user;
	}
}
