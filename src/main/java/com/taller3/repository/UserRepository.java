package com.taller3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.taller3.model.person.UserApp;
import com.taller3.model.person.UserType;


public interface UserRepository extends CrudRepository<UserApp, Long> {
	List<UserApp> findByUsername(String username);
	List<UserApp> findByType(UserType type);
}
