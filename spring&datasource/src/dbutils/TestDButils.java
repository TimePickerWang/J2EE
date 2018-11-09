package dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import jdbc.User;

public class TestDButils {

	// @Test
	public void testSelect() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<User> list = qr.query("select * from users", new ResultSetHandler<List<User>>() {
			public List<User> handle(ResultSet rs) throws SQLException {
				List<User> list = new ArrayList<User>();
				User user = null;
				while (rs.next()) {
					user = new User();
					user.setId(rs.getInt(1));
					user.setName(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setEmail(rs.getString(4));
					user.setBirthday(rs.getDate(5));
					list.add(user);
				}
				return list;
			}
		});
		for (User user : list) {
			System.out.println(user);
		}
	}

	// @Test
	public void testSelect2() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<User> list = qr.query("select * from users", new BeanListHandler<User>(User.class));
		for (User user : list) {
			System.out.println(user);
		}
	}

	// @Test
	public void testInsert() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		int i = qr.update("insert into users(name,password,email,birthday) values (?, ?, ?, ?)", "花大侠", "555",
				"555@qq.com", new Date());
		System.out.println(i);
	}

	// @Test
	public void testBatch() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Object[][] params = new Object[10][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[] { "菜1" + i, "555", "555@qq.com", new Date() };
		}
		// 批量处理，只能处理相同的sql语句
		qr.batch("insert into users(name,password,email,birthday) values (?,?,?,?)", params);
	}

	@Test
	public void testDelete() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		int num = qr.update("delete from users where name like '菜%'");
		System.out.println(num);
	}
}
