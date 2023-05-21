package com.cog.employee.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer employeeId;
	@NotBlank(message = "Name can't be blank/null.")
	private String name;
	@Min(value = 18,message = "Age should be greater than 0.")
	private Integer age;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@NotBlank(message = "Address can't be blank/null.")
	private String address;
	@Enumerated(EnumType.STRING)
	private IsActive isActive = IsActive.Y;

}
