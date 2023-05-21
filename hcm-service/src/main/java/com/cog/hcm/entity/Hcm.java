package com.cog.hcm.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hcm {

	@Id
	private Integer employeeId;
	@Min(value = 0, message = "Experience should be greater than or equals to zero.")
	private Integer experience;
	@Min(value = 0, message = "Experience should be greater than or equals to zero.")
	private Integer yearsInCurrentRole;
	@Enumerated(EnumType.STRING)
	@NotNull(message = "GoalCompletedForCurrentYear can't be null.")
	private GoalCompletedForCurrentYear goalCompletedForCurrentYear;
	@Enumerated(EnumType.STRING)
	@NotNull(message = "GoalCompletedForCurrentYear can't be null.")
	private ClientAppreciationForCurrentYear clientAppreciationForCurrentYear;

}
