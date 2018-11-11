package mybatis02;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class MybatisSecond {
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		// 读取配置文件
		// 全局配置文件的路径
		String resources = "mybatis02/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resources);

		// 创建SqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindUserById() throws Exception {
		// 创建UserMapper对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 由mybatis通过sqlsession来创建代理对象
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		
		User user = mapper.findUserById(1);
		System.out.println(user);
		sqlSession.close();
	}

	@Test
	public void testInsertUser() throws Exception {
		// 创建UserMapper对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 由mybatis通过sqlsession来创建代理对象
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername("东哥hm19");
		user.setAddress("宝盛西里24号楼");

		mapper.insertUser(user);
		System.out.println(user.getId());
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testFindUserRstMap() throws Exception {
		// 创建UserMapper对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 由mybatis通过sqlsession来创建代理对象
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		
		User user = mapper.findUserRstMap(1);
		System.out.println(user);
		sqlSession.close();
	}

}
