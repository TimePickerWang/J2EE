package c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.User;

public class C3P0DoLogin {

	public User findUser(String name, String pwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("select * from users where name = ? and password = ?");

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
			C3P0Util.release(conn, ps, rs);
		}
		return user;
	}
}
