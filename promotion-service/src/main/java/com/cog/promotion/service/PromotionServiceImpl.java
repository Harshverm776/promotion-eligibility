package com.cog.promotion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cog.promotion.constants.Constants;
import com.cog.promotion.domain.ClientAppreciationForCurrentYear;
import com.cog.promotion.domain.GoalCompletedForCurrentYear;
import com.cog.promotion.domain.HcmResponse;
import com.cog.promotion.domain.IsEligibleForPromotion;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public IsEligibleForPromotion isEligible(Integer employeeId) {
		log.info("isEligible service called.");
		HcmResponse hcm = restTemplate.getForObject(Constants.GET_HCMBYID_ENDPOINT_URL + "/" + employeeId,
				HcmResponse.class);
		log.info("Hcm Response-"+hcm.toString());
		if (hcm.getYearsInCurrentRole() >= 3
				&& (hcm.getGoalCompletedForCurrentYear().equals(GoalCompletedForCurrentYear.Y)
						|| hcm.getClientAppreciationForCurrentYear().equals(ClientAppreciationForCurrentYear.Y)))
			return IsEligibleForPromotion.Y;
		else
			return IsEligibleForPromotion.N;
	}
}
