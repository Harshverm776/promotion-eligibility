package com.cog.employee.converter;

import org.springframework.stereotype.Component;

import com.cog.employee.domain.EmployeeResponse;
import com.cog.employee.entity.Employee;

@Component
public class EmployeeConverter {
	public EmployeeResponse convertToDto(Employee employee) {
		return EmployeeResponse.builder()
				.employeeId(employee.getEmployeeId())
				.name(employee.getName())
				.age(employee.getAge())
				.gender(employee.getGender())
				.address(employee.getAddress())
				.build();
	}
}
