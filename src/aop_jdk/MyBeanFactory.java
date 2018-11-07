package aop_jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanFactory {
	public static UserService createService() {
		// 1.������
		final UserService userService = new UserServiceImpl();
		// 2.������
		final MyAspect myAspect = new MyAspect();
		
		/* 3 �����ࣺ��Ŀ���ࣨ����㣩�� �����֪ࣨͨ�� ��� --> ����
		 * 	Proxy.newProxyInstance
		 * 		����1��loader �������������̬������ ����ʱ�������κ��඼��Ҫ�������������ص��ڴ档
		 * 			һ���������ǰ��.class.getClassLoader();
		 * 				        Ŀ����ʵ��.getClass().getClassLoader()
		 * 		����2��Class[] interfaces ��������Ҫʵ�ֵ����нӿ�
		 * 			��ʽ1��Ŀ����ʵ��.getClass().getInterfaces()  ;ע�⣺ֻ�ܻ���Լ��ӿڣ����ܻ�ø�Ԫ�ؽӿ�
		 * 			��ʽ2��new Class[]{UserService.class}   
		 * 		����3��InvocationHandler  �����࣬�ӿڣ��������ʵ���࣬һ����������ڲ�
		 * 			�ṩ invoke �������������ÿһ������ִ��ʱ����������һ��invoke
		 * 				����31��Object proxy ���������
		 * 				����32��Method method : �������ǰִ�еķ������������󣨷��䣩
		 * 					ִ�з�������method.getName()
		 * 					ִ�з�����method.invoke(����ʵ�ʲ���)
		 * 				����33��Object[] args :����ʵ�ʲ���
		 */
		UserService proxyService = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
				userService.getClass().getInterfaces(), new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// ǰִ��
						myAspect.before();
						
//						System.out.println(method.getName());
						
						Object obj = method.invoke(userService, args);

						// ��ִ��
						myAspect.after();

						return obj;
					}
				});

		return proxyService;
	}
}
