package com.cog.employee.domain;

import com.cog.employee.entity.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeResponse {

	private int employeeId;
	private String name;
	private int age;
	private Gender gender;
	private String address;
	private IsEligibleForPromotion isEligibleForPromotion;
}
