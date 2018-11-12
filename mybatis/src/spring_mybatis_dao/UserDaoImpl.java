package spring_mybatis_dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Override
	public User findUserById(int id) throws Exception {
		SqlSession sqlSession = this.getSqlSession();
		User user = sqlSession.selectOne("User.findUserById", 1);
		return user;
	}

}
