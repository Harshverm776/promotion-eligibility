package com.cog.news.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

	@Min(value = 1,message = "Severity should be greater than 0.")
	@Max(value = 5,message = "Severity should be less than 6.")
	@NotNull(message = "Severity can't be null")
	private Integer severity;
	@NotBlank(message = "Reason can't be blank/null.")
	private String reason;
	@NotNull(message = "ForceTermination can't be null")
	private ForceTermination forceTermination;
}
