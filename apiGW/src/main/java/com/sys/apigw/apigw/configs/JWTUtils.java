package com.sys.apigw.apigw.configs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;



@Component
public class JWTUtils {
	
	@Value("${jwt.secret}")
	private String jwtSecret;

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}
	
	public boolean isTokenExpired(String token) {
		Claims claims=this.getAllClaimsFromToken(token);
		return claims.getExpiration().before(new Date());
	}
	
	
}
