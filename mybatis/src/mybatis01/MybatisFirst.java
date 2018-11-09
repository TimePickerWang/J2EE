package mybatis01;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class MybatisFirst {

	@Test
	public void findByUserId() throws Exception {
		// 读取配置文件
		// 全局配置文件的路径
		String resources = "mybatis01/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resources);

		// 创建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 调用SqlSession的增删改查方法
		// 第一个参数：表示statement的唯一标示
		User user = sqlSession.selectOne("User.findUserById", 1);
		System.out.println(user);

		// 关闭资源
		sqlSession.close();
	}

	@Test
	public void findByUserName() throws Exception {
		String resources = "mybatis01/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resources);

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession sqlSession = sqlSessionFactory.openSession();

		List<User> userList = sqlSession.selectList("User.findByUserName", "小明");
		System.out.println(userList);

		sqlSession.close();
	}

	@Test
	public void insertUser() throws Exception {
		String resources = "mybatis01/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resources);

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession sqlSession = sqlSessionFactory.openSession();

		User user = new User();
		user.setUsername("freeman");
		user.setAddress("湖北工业大学");

		sqlSession.insert("User.insertUser", user);
		
		System.out.println(user.getId());
		// 提交事务
		sqlSession.commit();

		sqlSession.close();
	}

}
