package com.phystrix.demo.service;

import org.springframework.stereotype.Service;

import com.guard.phystrix.Phystrix;

@Service
public class RemoteService {

	
	@Phystrix(commandKey="key",commandGroup="group",fallBack="requestFallBack")
	public String requestRemote(String a,String b){
		System.out.println("");
		return "fsafasfa";
	}
	
	
	public String requestFallBack(String a,String b){
		return "aaaaaaaaaaaaaaaaaaaaa";
	}
}
