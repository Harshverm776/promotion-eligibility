package com.cog.hcm.service;

import java.util.List;

import com.cog.hcm.entity.Hcm;

public interface HcmService {
	
	Integer createHcm(Hcm hcm);
	
	Integer updateHcm(Hcm hcm);

	Hcm readHcmById(Integer employeeId);

	List<Hcm> readAllHcm();

}
