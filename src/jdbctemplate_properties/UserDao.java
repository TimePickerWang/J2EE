package jdbctemplate_properties;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import jdbc.User;

public class UserDao extends JdbcDaoSupport {

	public void updata(User user) {
		String sql = "update users set name=?,email=? where id=?";
		Object[] args = { user.getName(), user.getEmail(), user.getId() };
		this.getJdbcTemplate().update(sql, args);
	}

}
