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
		// ��ȡ�����ļ�
		// ȫ�������ļ���·��
		String resources = "mybatis02/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resources);

		// ����SqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindUserById() throws Exception {
		// ����UserMapper����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��mybatisͨ��sqlsession�������������
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		
		User user = mapper.findUserById(1);
		System.out.println(user);
		sqlSession.close();
	}

	@Test
	public void testInsertUser() throws Exception {
		// ����UserMapper����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��mybatisͨ��sqlsession�������������
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername("����hm19");
		user.setAddress("��ʢ����24��¥");

		mapper.insertUser(user);
		System.out.println(user.getId());
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testFindUserRstMap() throws Exception {
		// ����UserMapper����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��mybatisͨ��sqlsession�������������
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		
		User user = mapper.findUserRstMap(1);
		System.out.println(user);
		sqlSession.close();
	}

}
