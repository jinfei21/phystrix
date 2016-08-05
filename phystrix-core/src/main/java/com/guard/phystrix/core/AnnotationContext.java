package com.guard.phystrix.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnnotationContext {
	private String commandKey;
	private String commandGroup;
	private String fallBack;
	
	public AnnotationContext(String commandGroup,String commandKey,String fallBack){
		this.commandKey = commandKey;
		this.commandGroup = commandGroup;
		this.fallBack = fallBack;		
	}
}
