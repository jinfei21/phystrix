package com.phystrix.demo.service;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guard.phystrix.Phystrix;
import com.phystrix.demo.dao.daointerface.ProfileDAO;
import com.phystrix.demo.dao.daoobject.ProfileDO;

@Service("profileService")
public class ProfileService {
	
	@Autowired
	private ProfileDAO profileDao;
	
	@Transactional
	@Phystrix(commandKey="profile",commandGroup="user",fallBack="requestFallBack")
	public ProfileDO getUserProfile(String userId){
		Random random = new Random();
		int num = random.nextInt(1500);
		if(num < 500){
			throw new RuntimeException("throw get profile exception!");
		}
		try {
			TimeUnit.MILLISECONDS.sleep(num);
		} catch (InterruptedException e) {}
		
		System.out.println("-----get user profile of " + userId);
		
		return profileDao.getProfileByUserId(userId);
	}
	
	
	public ProfileDO requestFallBack(String userId){
		System.out.println("-----error: get user profile failed!");
		return null;
	}
}
