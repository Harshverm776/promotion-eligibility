package com.cog.news.processor;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cog.news.constants.Constants;
import com.cog.news.domain.ClientAppreciationForCurrentYear;
import com.cog.news.domain.GoalCompletedForCurrentYear;
import com.cog.news.domain.Hcm;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmployeeDataProcessor {

	@Autowired
	private RestTemplate restTemplate;

	public void processIsActive() {

		log.info("EmployeeDataProcessor call with processIsActive");
		Hcm[] hcmResponse = restTemplate.getForObject(Constants.GET_ALLHCM_ENDPOINT_URL, Hcm[].class);

		log.info("All HCM data received successfully from HCM service-" + hcmResponse.toString());
		List<Hcm> hcmList = Arrays.asList(hcmResponse);

		for (Hcm hcm : hcmList) {

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>("", headers);

			String isActive = "";
			if (hcm.getYearsInCurrentRole() >= 2
					&& (hcm.getGoalCompletedForCurrentYear().equals(GoalCompletedForCurrentYear.Y)
							|| hcm.getClientAppreciationForCurrentYear().equals(ClientAppreciationForCurrentYear.Y)))
				isActive = "Y";
			else
				isActive = "N";

			restTemplate.exchange("http://localhost:8081/employee/" + hcm.getEmployeeId() + "?isActive=" + isActive,
					HttpMethod.PUT, entity, String.class);
			log.info("Update IsActive service called for employee-"+hcm.getEmployeeId());
			
		}

	}
}
