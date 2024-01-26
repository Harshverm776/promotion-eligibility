package com.cog.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.cog.employee.converter.EmployeeConverter;
import com.cog.employee.domain.EmployeeResponse;
import com.cog.employee.domain.IsEligibleForPromotion;
import com.cog.employee.entity.Employee;
import com.cog.employee.entity.IsActive;
import com.cog.employee.exception.EmployeeNotFoundException;
import com.cog.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EmployeeConverter employeeConverter;
	
	
	@Value("${GET_ISELIGIBLE_ENDPOINT_URL}")
	private String getIsElgibleEndpoint;

	@Override
	public Integer createEmployee(Employee employee) {
		Employee employeeDetails = employeeRepository.save(employee);
		log.info("Employee data saved successfully-"+employee.toString());
		return employeeDetails.getEmployeeId();
	}

	@Override
	@Transactional
	public void deleteEmployee(Optional<Integer> employeeId) {
		if (employeeId.isPresent())
			employeeRepository.deleteById(employeeId.get());
		else
			employeeRepository.deleteByIsActive(IsActive.N);
		log.info("Employee deleted successfully");
	}
	
	@Override
	public List<EmployeeResponse> getAllEmployee() {
		List<Employee> employees = employeeRepository.findAll();
		if(!employees.isEmpty()) {
			return employees.stream().map(employeeConverter::convertToDto).collect(Collectors.toList());
		} else {
			throw new EmployeeNotFoundException("Employees are not present");
		}
	}

	@Override
	public Integer updateEmployee(Employee employee) {
		Employee existingEmployee = employeeRepository.findById(employee.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException("Employee doesn't exists."));

		existingEmployee.setName(employee.getName());
		existingEmployee.setAge(employee.getAge());
		existingEmployee.setGender(employee.getGender());
		existingEmployee.setAddress(employee.getAddress());
		existingEmployee.setIsActive(employee.getIsActive());

		return employeeRepository.save(employee).getEmployeeId();
	}

	@Override
	public List<EmployeeResponse> readAllEmployee(IsActive isActive) {
		List<Employee> employeeList = employeeRepository.findAllByIsActive(isActive);

		List<EmployeeResponse> employeeResponseList = new ArrayList<>();

		for (int i = 0; i < employeeList.size(); i++) {
			EmployeeResponse employeeResponse = new EmployeeResponse();
			employeeResponse.setEmployeeId(employeeList.get(i).getEmployeeId());
			employeeResponse.setName(employeeList.get(i).getName());
			employeeResponse.setAge(employeeList.get(i).getAge());
			employeeResponse.setGender(employeeList.get(i).getGender());
			employeeResponse.setAddress(employeeList.get(i).getAddress());
			String status = restTemplate.getForObject(getIsElgibleEndpoint, String.class,
					employeeList.get(i).getEmployeeId());
			employeeResponse.setIsEligibleForPromotion(
					IsEligibleForPromotion.valueOf(status.substring(1, status.length() - 1)));
			employeeResponseList.add(employeeResponse);
		}
		log.info("List of eligible employees-"+employeeResponseList.toString());
		return employeeResponseList;
	}

	@Override
	public void updateIsActive(Integer employeeId, IsActive isActive) {
		Employee existingEmployee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee doesn't exists."));

		existingEmployee.setIsActive(isActive);
		employeeRepository.save(existingEmployee);
		log.info("IsActive updated successfully for employee-"+employeeId);
	}

}
