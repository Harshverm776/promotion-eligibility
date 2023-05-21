package com.cog.employee.service;

import java.util.List;
import java.util.Optional;

import com.cog.employee.domain.EmployeeResponse;
import com.cog.employee.entity.Employee;
import com.cog.employee.entity.IsActive;

public interface EmployeeService {
	
	Integer createEmployee(Employee employee);
	
	void deleteEmployee(Optional<Integer> employeeId);
	
	Integer updateEmployee(Employee employee);
	
	List<EmployeeResponse> readAllEmployee(IsActive isActive);

	void updateIsActive(Integer employeeId, IsActive isActive);
}
