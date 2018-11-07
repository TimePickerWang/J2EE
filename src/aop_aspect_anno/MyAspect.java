package aop_aspect_anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面类，含有多个通知
 */
@Component
@Aspect
public class MyAspect {
	// 声明公共切入点
	@Pointcut("execution(* aop_aspect_anno.UserServiceImpl.*(..))")
	private void pointCut() {
	}

	// 切入点当前有效
	// @Before("execution(* aop_aspect_anno.UserServiceImpl.*(..))")
	public void before(JoinPoint joinPoint) {
		System.out.println("before" + joinPoint.getSignature().getName());
	}

	// @AfterReturning(value="pointCut()",returning="ret")
	public void afterReturning(JoinPoint joinPoint, Object ret) {
		System.out.println("after" + joinPoint.getSignature().getName() + ",--->" + ret);
	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("before");
		// 手动执行目标方法
		Object obj = proceedingJoinPoint.proceed();
		System.out.println("after");
		return obj;
	}

	// @AfterThrowing(value = "execution(* aop_aspect_anno.UserServiceImpl.*(..))", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Throwable e) {
		System.out.println("抛出异常通知：" + e.getMessage());
	}

	// @After("pointCut()")
	public void after(JoinPoint joinPoint) {
		System.out.println("最终通知");
	}
}
