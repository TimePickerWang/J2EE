package jdbctemplate_c3p0;

import org.springframework.jdbc.core.JdbcTemplate;

import jdbc.User;

public class UserDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void updata(User user) {
		String sql = "update users set name=?,email=? where id=?";
		Object[] args = { user.getName(), user.getEmail(), user.getId() };
		this.jdbcTemplate.update(sql, args);
	}
}
