package com.cog.employee.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cog.employee.entity.Employee;
import com.cog.employee.entity.Gender;
import com.cog.employee.entity.IsActive;
import com.cog.employee.exception.EmployeeNotFoundException;
import com.cog.employee.repository.EmployeeRepository;

//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmployeeServiceImplTest {

	@MockBean
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;

	@Test
	void testCreateEmployee() {
		Employee employee = new Employee(1, "Harsh Verma", 24, Gender.MALE, "Indore", IsActive.Y);
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(1, employeeService.createEmployee(employee));
	}

	@Test
	void testDeleteEmployeeIsPresent() {
		Optional<Integer> employeeId = Optional.of(1);
		doNothing().when(employeeRepository).deleteById(employeeId.get());
		employeeService.deleteEmployee(employeeId);
		verify(employeeRepository, times(1)).deleteById(employeeId.get());
	}

	@Test
	void testDeleteEmployeeIsNotPresent() {
		doNothing().when(employeeRepository).deleteByIsActive(IsActive.N);
		employeeService.deleteEmployee(Optional.empty());
		verify(employeeRepository, times(1)).deleteByIsActive(IsActive.N);
	}

	@Test
	void testUpdateEmployee() {
		Employee employee = new Employee(1, "Harsh Verma", 24, Gender.MALE, "Indore", IsActive.Y);
		when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(1, employeeService.updateEmployee(employee));
	}

	@Test
	void testUpdateEmployeeEmployeeNotFoundException() {
		Employee employee = new Employee(1, "Harsh Verma", 24, Gender.MALE, "Indore", IsActive.Y);
		when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.empty());
		Exception exception = assertThrows(EmployeeNotFoundException.class,
				() -> employeeService.updateEmployee(employee));
		assertEquals("Employee doesn't exists.", exception.getMessage());
	}
}
