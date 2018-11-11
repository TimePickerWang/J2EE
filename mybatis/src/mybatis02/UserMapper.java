package mybatis02;

import mybatis02.User;

public interface UserMapper {
	public User findUserById(int id) throws Exception;

	public void insertUser(User user) throws Exception;
	
	public User findUserRstMap(int id) throws Exception;
}
