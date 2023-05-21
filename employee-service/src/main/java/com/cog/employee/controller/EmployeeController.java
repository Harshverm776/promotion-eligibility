package com.cog.employee.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cog.employee.domain.EmployeeResponse;
import com.cog.employee.entity.Employee;
import com.cog.employee.entity.IsActive;
import com.cog.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Integer> createEmployee(@RequestBody @Valid Employee employee) {
		return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
	}

	@DeleteMapping()
	public ResponseEntity<?> deleteEmployee(@RequestParam Optional<Integer> employeeId) {
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Integer> updateEmployee(@RequestBody @Valid Employee employee) {
		return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeResponse>> readAllEmployee(@RequestParam IsActive isActive) {
		return new ResponseEntity<>(employeeService.readAllEmployee(isActive), HttpStatus.OK) ;
	}
	
	@PutMapping("{employeeId}")
	public ResponseEntity<?> updateIsActive(@PathVariable Integer employeeId, @RequestParam IsActive isActive){
		employeeService.updateIsActive(employeeId, isActive);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
