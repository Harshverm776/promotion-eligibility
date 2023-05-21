package com.cog.news.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hcm {

	private Integer employeeId;
	private Integer experience;
	private Integer yearsInCurrentRole;
	private GoalCompletedForCurrentYear goalCompletedForCurrentYear;
	private ClientAppreciationForCurrentYear clientAppreciationForCurrentYear;

}
