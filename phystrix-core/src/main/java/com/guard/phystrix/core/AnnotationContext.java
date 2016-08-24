package com.guard.phystrix.core;


public class AnnotationContext {
	private String commandKey;
	private String commandGroup;
	private String fallBack;
	private String isolationStgy;
	private int maxRequest;
	private int timeout;

	public AnnotationContext(String commandGroup, String commandKey, String fallBack, String isolationStrategy, int maxRequest, int timeout) {
		this.commandKey = commandKey;
		this.commandGroup = commandGroup;
		this.fallBack = fallBack;
		this.isolationStgy = isolationStrategy.toUpperCase();
		this.maxRequest = maxRequest;
		this.timeout = timeout;
	}

	public AnnotationContext(String commandGroup, String commandKey, String fallBack, String isolationStrategy) {
		this.commandKey = commandKey;
		this.commandGroup = commandGroup;
		this.fallBack = fallBack;
		this.isolationStgy = isolationStrategy.toUpperCase();
		this.maxRequest = 500;
		this.timeout = 1000;
	}
	
	public String getCommandKey() {
		return commandKey;
	}

	public void setCommandKey(String commandKey) {
		this.commandKey = commandKey;
	}

	public String getCommandGroup() {
		return commandGroup;
	}

	public void setCommandGroup(String commandGroup) {
		this.commandGroup = commandGroup;
	}

	public String getFallBack() {
		return fallBack;
	}

	public void setFallBack(String fallBack) {
		this.fallBack = fallBack;
	}

	public int getMaxRequest() {
		return maxRequest;
	}

	public void setMaxRequest(int maxRequest) {
		this.maxRequest = maxRequest;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getIsolationStgy() {
		return isolationStgy;
	}

	public void setIsolationStgy(String isolationStgy) {
		this.isolationStgy = isolationStgy.toUpperCase();
	}
	
	
}
