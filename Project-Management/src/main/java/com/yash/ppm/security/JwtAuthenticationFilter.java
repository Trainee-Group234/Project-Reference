package com.yash.ppm.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yash.ppm.model.User;
import com.yash.ppm.serviceImpl.CustomUserDetailsService;

import antlr.StringUtils;
import lombok.extern.log4j.Log4j;

@Log4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String jwt = getJwtFromRequest(request);
			if(jwt!=null && tokenProvider.validateToken(jwt)) {
				Long userID = tokenProvider.getUserIdFromToken(jwt);
				User userDetails = customUserDetailsService.loadByUserId(userID);
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,Collections.emptyList());
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}catch(Exception e) {
			log.info(e);
		}
		filterChain.doFilter(request, response);
	}
	
	private String getJwtFromRequest(HttpServletRequest req) {
		String bearerToken = req.getHeader(SecurityConstants.HEADER_STRING);
		
		if(bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			return bearerToken.substring(7,bearerToken.length());
		}
		
		return null;
	}

}
