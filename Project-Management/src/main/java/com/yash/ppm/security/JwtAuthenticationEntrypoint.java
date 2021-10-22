package com.yash.ppm.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.yash.ppm.exception.InvalidLoginResponse;

@Component
public class JwtAuthenticationEntrypoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		InvalidLoginResponse resp = new InvalidLoginResponse();
		String jsonResponse = 	new Gson().toJson(resp);
		
		response.setContentType("application/json");
		response.setStatus(401);
		response.getWriter().print(jsonResponse);
	}
	
	
}
