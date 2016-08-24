package com.guard.phystrix.core;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.ReflectionUtils;

import com.guard.phystrix.util.ReflectUtil;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.HystrixTimerThreadPoolProperties;

public class PHystrixCommand extends HystrixCommand<Object> {

	private ProceedingJoinPoint pjp;
	private AnnotationContext commandContext;

	public PHystrixCommand(Setter setter) {
		super(setter);
	}

	public PHystrixCommand(AnnotationContext commandContext, ProceedingJoinPoint pjp) {
		this(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(commandContext.getCommandGroup()))
				.andCommandKey(HystrixCommandKey.Factory.asKey(commandContext.getCommandKey()))
			    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
				  .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.valueOf(commandContext.getIsolationStgy()))
				  .withExecutionIsolationSemaphoreMaxConcurrentRequests(commandContext.getMaxRequest())
				  .withExecutionTimeoutInMilliseconds(commandContext.getTimeout())));
		this.pjp = pjp;
		this.commandContext = commandContext;
	}

		
	@Override
	protected Object run() throws Exception {
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected Object getFallback() {
		if (StringUtils.isNotEmpty(commandContext.getFallBack())) {
			Method method = ReflectionUtils.findMethod(pjp.getTarget().getClass(), commandContext.getFallBack(),
					ReflectUtil.toTargetClazz(pjp.getArgs()));
			ReflectionUtils.makeAccessible(method);
			return ReflectionUtils.invokeMethod(method, pjp.getTarget(), pjp.getArgs());
		}
		throw new UnsupportedOperationException("No fallback available.");
	}

}
