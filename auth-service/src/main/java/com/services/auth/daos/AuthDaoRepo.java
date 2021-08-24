package com.services.auth.daos;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.services.auth.beans.UserCredentials;

@Repository
public interface AuthDaoRepo extends MongoRepository<UserCredentials, String>{

	  Optional<UserCredentials> findByUsername(String username);

	  Boolean existsByUsername(String username);
}
