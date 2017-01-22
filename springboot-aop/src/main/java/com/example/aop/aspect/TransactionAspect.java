
package com.example.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * spring aop支持的通知：
 * @Before：前置通知：在某连接点之前执行的通知，但这个通知不能阻止连接点之前的执行流程（除非它抛出一个异常）<BR>
 * @AfterReturning：后置通知：在某连接点正常完成后执行的通知，通常在一个匹配的方法返回的时候执行。可以在后置通知中绑定返回值<BR>
 * @AfterThrowing:异常通知：在方法抛出异常退出时执行的通知<BR>
 * 
 * @Around：环绕通知：包围一个连接点的通知，如方法调用。这是最强大的一种通知类型。 环绕通知可以在方法调用前后完成自定义的行为。
 *                                             它也会选择是否继续执行连接点或直接返回它自己的返回值或抛出异常来结束执行
 * @After 最终通知。当某连接点退出的时候执行的通知（不论是正常返回还是异常退出）<BR>
 * 
 * @author Enyeama
 *
 */
@Aspect
@Component
public class TransactionAspect extends Transaction {

	Logger LOGGER = LoggerFactory.getLogger(TransactionAspect.class);

	/**
	 * <p>
	 * 配置切入点
	 * </p>
	 * aspect方法无方法体主要方便本类的其他method使用这个pointcut
	 */
	@Pointcut("execution(* *com.example.aop.service..*.*(..))")
	public void aspect() {
	}

	/**
	 * 配置环绕切面
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("aspect()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();

		if (methodName.contains("get")) {
			return pjp.proceed();
		} else {
			start(methodName);
			Object result = pjp.proceed();
			LOGGER.info("Executes {} return {}", methodName, result);
			commit(methodName);
			return result;
		}
	}

	/**
	 * <p>
	 * 配置前置通知
	 * </p>
	 * 
	 * @param jp
	 * @throws Throwable
	 */
	@Before("aspect()")
	public void before(JoinPoint jp) throws Throwable {
		String methodName = jp.getSignature().getName();
		LOGGER.info("Execute advice before {}", methodName);
	}

	/**
	 * <p>
	 * 配置最终通知
	 * </p>
	 * 
	 * @param jp
	 * @throws Throwable
	 */
	@After("aspect()")
	public void after(JoinPoint jp) throws Throwable {
		String methodName = jp.getSignature().getName();
		LOGGER.info("Execute advice after {}", methodName);
	}

	/**
	 * <p>
	 * 配置后置通知
	 * </p>
	 * 
	 * @param returnVal
	 * @throws Throwable
	 */
	@AfterReturning(value = "aspect()", returning = "returnVal")
	public void afterReturning(Object returnVal) throws Throwable {
		LOGGER.info("Execute aspect afterReturning {}, and the return value is {}", returnVal);
	}

	/**
	 * <p>
	 * 配置后置通知
	 * </p>
	 * this：用于向通知方法中传入代理对象的引用
	 * 
	 * @param jp
	 * @throws Throwable
	 */
	@AfterReturning("aspect() && this(proxy)")
	public Object afterReturning(JoinPoint jp, Object proxy) throws Throwable {
		String methodName = jp.getSignature().getName();
		LOGGER.info("Execute aspect afterReturning {}, and proxy object is {}", methodName, proxy);
		return proxy;
	}

	/**
	 * <p>
	 * 配置后置异常通知
	 * </p>
	 * 
	 * @param jp
	 * @throws Throwable
	 */
	@AfterThrowing("aspect()")
	public void afterThrowing(JoinPoint jp) throws Throwable {
		String methodName = jp.getSignature().getName();
		LOGGER.info("Execute aspect afterThrowing {}", methodName);
	}

}
