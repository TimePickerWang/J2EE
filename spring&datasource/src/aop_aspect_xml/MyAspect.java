package aop_aspect_xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面类，含有多个通知
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
		// 手动执行目标方法
		Object obj = proceedingJoinPoint.proceed();
		System.out.println("after");
		return obj;
	}

	public void afterThrowing(JoinPoint joinPoint, Throwable e) {
		System.out.println("抛出异常通知：" + e.getMessage());
	}

	public void after(JoinPoint joinPoint) {
		System.out.println("最终通知");
	}
}
