package com.guard.phystrix;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
@Inherited
public @interface Phystrix {

	String commandKey() ;
	String commandGroup() ;
	String fallBack() default "";
	int maxRequest  = 500;
	int timeout = 500;
}
