package com.guard.phystrix.core;

import org.aspectj.lang.ProceedingJoinPoint;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

public class PHystrixCommand extends HystrixCommand<Object>{
	
	private ProceedingJoinPoint pjp;

	public PHystrixCommand(Setter setter) {
		super(setter);
	}
	
	public PHystrixCommand(String group,String key,ProceedingJoinPoint pjp){
		this(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(group))  
                .andCommandKey(HystrixCommandKey.Factory.asKey(key)));
		this.pjp = pjp;
	}

	@Override
	protected Object run() throws Exception {
        try {
			return pjp.proceed();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}

	}

}
