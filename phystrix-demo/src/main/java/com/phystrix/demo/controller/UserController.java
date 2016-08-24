package com.phystrix.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phystrix.demo.common.Result;
import com.phystrix.demo.dao.daoobject.ProfileDO;
import com.phystrix.demo.service.ProfileService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private ProfileService profileService;

	@RequestMapping(value = "/{userId}/profile", method = RequestMethod.GET)
	@ResponseBody
	public Result<ProfileDO> getUserProfile(@PathVariable String userId) {
		ProfileDO profileDo = profileService.getUserProfile(userId);
		Result<ProfileDO> result = new Result<ProfileDO>();
		boolean success = profileDo==null ? false : true;
		String message = profileDo==null ? "error: get user profile failed!" : "OK";
		result.setSuccess(success);
		result.setResult(profileDo);
		result.setMessages(message);
		return result;
	}
	
}
