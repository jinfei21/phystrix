package com.guard.phystrix.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnnotationContext {
	private String commandKey;
	private String commandGroup;
	private String fallBack;
	private int maxRequest;
	private int timeout;
	
	public AnnotationContext(String commandGroup,String commandKey,String fallBack,int maxRequest,int timeout){
		this.commandKey = commandKey;
		this.commandGroup = commandGroup;
		this.fallBack = fallBack;
		this.maxRequest = maxRequest;
		this.timeout = timeout;
	}
	
	public AnnotationContext(String commandGroup,String commandKey,String fallBack){
		this.commandKey = commandKey;
		this.commandGroup = commandGroup;
		this.fallBack = fallBack;
		this.maxRequest = 500;
		this.timeout = 5000;
	}
}
