package com.cog.hcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cog.hcm.entity.Hcm;
import com.cog.hcm.exception.HcmAlreadyExistException;
import com.cog.hcm.exception.HcmNotFoundException;
import com.cog.hcm.repository.HcmRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HcmServiceImpl implements HcmService {

	@Autowired
	private HcmRepository hcmRepository;

	@Override
	public Integer createHcm(Hcm hcm) {
		if (hcmRepository.existsById(hcm.getEmployeeId()))
			throw new HcmAlreadyExistException("Employee details already exist.");

		Hcm hcmDetails = hcmRepository.save(hcm);
		log.info("Hcm details successfully created for the employee"+hcm.getEmployeeId());
		return hcmDetails.getEmployeeId();
	}

	@Override
	public Integer updateHcm(Hcm hcm) {
		Hcm existingHcm = hcmRepository.findById(hcm.getEmployeeId())
				.orElseThrow(() -> new HcmNotFoundException("HCM Details doesn't exists."));

		existingHcm.setExperience(hcm.getExperience());
		existingHcm.setYearsInCurrentRole(hcm.getYearsInCurrentRole());
		existingHcm.setGoalCompletedForCurrentYear(hcm.getGoalCompletedForCurrentYear());
		existingHcm.setClientAppreciationForCurrentYear(hcm.getClientAppreciationForCurrentYear());

		return hcmRepository.save(hcm).getEmployeeId();
	}

	@Override
	public Hcm readHcmById(Integer employeeId) {
		return hcmRepository.findById(employeeId).get();
	}

	@Override
	public List<Hcm> readAllHcm() {
		return hcmRepository.findAll();
	}
}
