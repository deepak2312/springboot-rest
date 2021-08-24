package com.services.auth.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.services.auth.beans.UserCredentials;
import com.services.auth.beans.UserData;
import com.services.auth.daos.AuthDaoRepo;
import com.services.auth.utils.Utility;

@Service
public class AuthService {
	
	Logger logger = LogManager.getLogger(AuthService.class);
	
	
	@Autowired
	AuthDaoRepo authDao;
	
	@Autowired
	Utility utility;

	public int authenticate(UserData userData) {
		// TODO Auto-generated method stub
		UserCredentials userCredentials=Optional.of(userData).map(ele-> new UserCredentials(ele.getUsername(), ele.getPassword())).get();
		UserCredentials response=authDao.findByUsername(userCredentials.getUsername()).orElse(null);
		if(response==null) {
			logger.info("User with  username : ["+userCredentials.getUsername()+"] doesn't exist");
			return -2;
		}

		if(utility.validatePasswords(userData.getPassword(), response.getPassword())) {
			logger.info("User with  username : ["+userCredentials.getUsername()+"] is authenticated");
			return 1;
		}else {
			logger.info("User with  username : ["+userCredentials.getUsername()+"] is not authenticated");
			return 0;
		}
	}


	public int register(UserData userData) {
		// TODO Auto-generated method stub
		try {
			UserCredentials userCredentials=Optional.of(userData).map(ele-> new UserCredentials(ele.getUsername(), 
																								utility.encodePassword(ele.getPassword())))
																							    .get();
			UserCredentials res=authDao.save(userCredentials);
			return 1;
		}catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}


	public String refreshToken(String expToken) {
		// TODO Auto-generated method stub
		return utility.generateRefreshToken(expToken);

	}

}
