package com.cog.employee.constants;

import org.springframework.stereotype.Component;

@Component
public class Constants {
	private Constants() {

	}

	public static final String GET_ISELIGIBLE_ENDPOINT_URL = "http://localhost:8083/promotion?employeeId={employeeId}";
}
