package aop_aspect_xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * �����࣬���ж��֪ͨ
 */
public class MyAspect {

	public void before(JoinPoint joinPoint) {
		System.out.println("before" + joinPoint.getSignature().getName());
	}

	public void afterReturning(JoinPoint joinPoint, Object ret) {
		System.out.println("after" + joinPoint.getSignature().getName() + ",--->" + ret);
	}

	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("before");
		// �ֶ�ִ��Ŀ�귽��
		Object obj = proceedingJoinPoint.proceed();
		System.out.println("after");
		return obj;
	}

	public void afterThrowing(JoinPoint joinPoint, Throwable e) {
		System.out.println("�׳��쳣֪ͨ��" + e.getMessage());
	}

	public void after(JoinPoint joinPoint) {
		System.out.println("����֪ͨ");
	}
}
