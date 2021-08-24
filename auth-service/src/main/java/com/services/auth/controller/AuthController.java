package com.services.auth.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.auth.beans.UserData;
import com.services.auth.services.AuthService;
import com.services.auth.utils.Utility;

@RestController
@RequestMapping(value="/auth/")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	Utility utiity;

	@PostMapping(value="/login",consumes = "application/json",produces="application/json")
	public ResponseEntity<String> login(@RequestBody UserData userData){
		
		int res=authService.authenticate(userData);
		if(res==1 ) {
			String token=utiity.generateToken(userData.getUsername());
			if(token==null) {
				return new ResponseEntity<String>("Error Generating Token", HttpStatus.INTERNAL_SERVER_ERROR);
			}else{
				return new ResponseEntity<String>(token, HttpStatus.OK);
			}
		}else {
			return new ResponseEntity<String>("Not Authenticated", HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@PostMapping(value="/register",consumes = "application/json",produces="application/json")
	public ResponseEntity<String> register(@RequestBody UserData userData){
		
		int res=authService.register(userData);
		if(res==1 ) {
				return new ResponseEntity<String>("Successfully Registered", HttpStatus.OK);
			
		}else {
			return new ResponseEntity<String>("Not Registered", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
	    String isRefresh = request.getHeader("isRefresh");
	    if(isRefresh!=null && isRefresh.equals("1")) {
	    	String auth=request.getHeader("Authorization");
	    	if(auth.startsWith("Bearer")) {
	    		String expToken=auth.substring(auth.indexOf(" ")+1);
	    		if(expToken!=null) {
	    				String token=authService.refreshToken(expToken);
	    				return new ResponseEntity<String>(token, HttpStatus.OK);
	    		}else {
	    			return new ResponseEntity<String>("No Token in Header",HttpStatus.BAD_REQUEST);
	    		}
	    		
	    	}else {
	    		return new ResponseEntity<String>("No Token in Header",HttpStatus.BAD_REQUEST);
	    	}
	    	
	    }else {
	    	return new ResponseEntity<String>("Incorrect Value of isRefresh",HttpStatus.BAD_REQUEST);
	    }
	}
}
