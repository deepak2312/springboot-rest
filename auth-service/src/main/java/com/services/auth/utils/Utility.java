package com.services.auth.utils;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.services.auth.services.AuthService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class Utility {
	
	Logger logger = LogManager.getLogger(Utility.class);
	
	@Value("${spring.security.salt}")
	String salt;
	
	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.token.validity}")
	private long tokenValidity;
	
	@Value("${jwt.refresh.token.validity}")
	private long refreshTokenValidity;
	
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public String encodePassword(String password) {
		System.out.println(bCryptPasswordEncoder.hashCode());
		return bCryptPasswordEncoder.encode(password);
	}
	
	public boolean validatePasswords(String newPass,String savedPass) {
		return bCryptPasswordEncoder.matches(newPass, savedPass);
	}
	
	public String generateToken(String username) {
		// TODO Auto-generated method stub
		Claims claims = Jwts.claims().setSubject(username);
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + tokenValidity;
		Date exp = new Date(expMillis);
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String generateRefreshToken(String expToken) {
		// TODO Auto-generated method stub
		
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(expToken).getBody();
			logger.info("The previous token hasn't expired so returing the token without any updates");
			return expToken;
		}catch(ExpiredJwtException ex) {
			Claims claims=ex.getClaims();
			long nowMillis = System.currentTimeMillis();
			long expMillis = nowMillis + refreshTokenValidity;
			Date exp = new Date(expMillis);
			return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
					.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		}
		
		
	}

}
