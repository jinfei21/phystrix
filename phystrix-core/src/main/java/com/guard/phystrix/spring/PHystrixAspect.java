package com.guard.phystrix.spring;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.Ordered;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.guard.phystrix.Phystrix;
import com.guard.phystrix.core.AnnotationContext;
import com.guard.phystrix.core.PHystrixCommand;
import com.guard.phystrix.infoboard.JettyServer;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class PHystrixAspect implements Ordered, ResourceLoaderAware, ApplicationContextAware {
	private ResourceLoader resourceLoader;

	private ApplicationContext applicationContext;
	
	private JettyServer jettyServer;
	
	
	private Cache<Method, AnnotationContext> commandCache = CacheBuilder.newBuilder().expireAfterAccess(300, TimeUnit.SECONDS).maximumSize(1000).build();
	
    @PostConstruct
    public void init(){
    	this.jettyServer = JettyServer.instance();
    	this.jettyServer.start();
    }
    
    @PreDestroy
    public void destroy(){
        this.jettyServer.shutdown();
    }
	
    @Pointcut("@annotation(com.guard.phystrix.Phystrix)")
    public void pointcutPHystrix(){}
    
    @Around("pointcutPHystrix()")
    public Object doPHystrix(ProceedingJoinPoint pjp) throws Throwable {
    	
        //获取方法上的注解内容
        final Method commandMethod = ((MethodSignature) pjp.getSignature()).getMethod();
        AnnotationContext commandContext = commandCache.getIfPresent(commandMethod);
        
        if(commandContext == null){
            Class<?> target = pjp.getTarget().getClass();
            Method targetMethod= target.getDeclaredMethod(commandMethod.getName(),commandMethod.getParameterTypes());
            Phystrix phystrix = targetMethod.getAnnotation(Phystrix.class);
            
            commandContext = new AnnotationContext(phystrix.commandGroup(),phystrix.commandKey(),phystrix.fallBack(),phystrix.isolationStgy());
            commandCache.put(commandMethod, commandContext);
        }
    	      
        return new PHystrixCommand(commandContext,pjp).execute();
      
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader= resourceLoader;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext= applicationContext;
    }

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

}
