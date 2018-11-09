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
		// ��ȡ�����ļ�
		// ȫ�������ļ���·��
		String resources = "mybatis01/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resources);

		// ����SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// ����SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// ����SqlSession����ɾ�Ĳ鷽��
		// ��һ����������ʾstatement��Ψһ��ʾ
		User user = sqlSession.selectOne("User.findUserById", 1);
		System.out.println(user);

		// �ر���Դ
		sqlSession.close();
	}

	@Test
	public void findByUserName() throws Exception {
		String resources = "mybatis01/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resources);

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession sqlSession = sqlSessionFactory.openSession();

		List<User> userList = sqlSession.selectList("User.findByUserName", "С��");
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
		user.setAddress("������ҵ��ѧ");

		sqlSession.insert("User.insertUser", user);
		
		System.out.println(user.getId());
		// �ύ����
		sqlSession.commit();

		sqlSession.close();
	}

}
