package com.yash.ppm.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.yash.ppm.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	public String generateToken(Authentication auth) {
		User user = (User) auth.getPrincipal();
		Date now  = new Date(System.currentTimeMillis());
		
		Date expiryDate = new Date(now.getTime()+SecurityConstants.EXPIRATION_TIME);
		
		String userId = Long.toString(user.getId());
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", (Long.toString(user.getId())));
		claims.put("username", user.getUsername());
		claims.put("fullname", user.getFullname());
		
		return Jwts.builder()
				.setSubject(userId).setClaims(claims)
				.setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
			return true;
		}catch(SignatureException e) {
			System.out.println("Invalid Jwt Signature");
		}catch(MalformedJwtException e) {
			System.out.println("Invalid Jwt Token");
		}catch(ExpiredJwtException e) {
			System.out.println("Expired Jwt Token");
		}catch(UnsupportedJwtException e) {
			System.out.println("Unsuppurted Jwt Token");
		}catch(IllegalArgumentException e) {
			System.out.println("Jwt claims String is empty");
		}
		return false;
	}
	
	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
		String id = (String) claims.get("id");
		return Long.parseLong(id);
	}
}
