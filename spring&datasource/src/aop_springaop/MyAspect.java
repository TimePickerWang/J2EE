package aop_springaop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAspect implements MethodInterceptor{

	public Object invoke(MethodInvocation mi) throws Throwable {
		
		System.out.println("before");
		
		Object obj = mi.proceed();
		
		System.out.println("after");
		
		return obj;
	}



}
