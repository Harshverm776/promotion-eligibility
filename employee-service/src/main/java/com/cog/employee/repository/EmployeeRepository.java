package com.cog.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cog.employee.entity.Employee;
import com.cog.employee.entity.IsActive;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	void deleteByIsActive(IsActive isActive);
	
	List<Employee> findAllByIsActive(IsActive isActive);
}
