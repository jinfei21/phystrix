package com.phystrix.demo.dao.daointerface;

import com.phystrix.demo.dao.daoobject.ProfileDO;

public interface ProfileDAO {

	ProfileDO getProfileById(Long id);
	
	ProfileDO getProfileByUserId(String userId);

	void insert(ProfileDO profileDO);

	void delete(Long id);

	void update(ProfileDO profileDO);
}
