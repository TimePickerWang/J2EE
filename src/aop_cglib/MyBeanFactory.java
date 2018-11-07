package aop_cglib;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class MyBeanFactory {
	public static UserServiceImpl createService() {
		// 1.������
		final UserServiceImpl userService = new UserServiceImpl();
		
		// 2.������
		final MyAspect myAspect = new MyAspect();
		
		// 3.������ ������cglib���ײ㴴��Ŀ���������
		// 3.1 ������
		Enhancer enhancer = new Enhancer();

		//3.2 ȷ������
		enhancer.setSuperclass(userService.getClass());
		
		/* 3.3 ���ûص����� , MethodInterceptor�ӿ� ��Ч jdk InvocationHandler�ӿ�
		 * 	intercept() ��Ч jdk  invoke()
		 * 		����1������2������3����invokeһ��
		 * 		����4��methodProxy �����Ĵ���
		 */
		enhancer.setCallback(new MethodInterceptor() {
			public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy)
					throws Throwable {
				// ǰִ��
				myAspect.before();

				Object obj = methodProxy.invoke(userService, args);
				// ��ִ��
				myAspect.after();

				return obj;
			}
		});
		
		//3.4 ��������
		UserServiceImpl proxyService = (UserServiceImpl) enhancer.create();

		return proxyService;
	}
}
