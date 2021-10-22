package com.yash.ppm.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {
	
	@NotBlank(message = "Username cant be blank")
	private String username;
	@NotBlank(message = "Username cant be blank")
	private String password;
}
