package com.taller3.service.implementation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taller3.model.person.UserApp;
import com.taller3.model.person.UserType;
import com.taller3.repository.UserRepository;
import com.taller3.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void save(UserApp user) {
		userRepository.save(user);
	}
	
	public void update(long id, UserApp user) {
		Optional<UserApp> toChange = userRepository.findById(id);
		if(toChange.isPresent()) {
			UserApp u = toChange.get();
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			u.setType(user.getType());
			userRepository.save(u);
		}
	}
	
	@Override
	public void delete(UserApp user) {
		userRepository.delete(user);
	}

	public Optional<UserApp> findById(long id) {
		return userRepository.findById(id);
	}

	public Iterable<UserApp> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Iterable<UserApp> findAllAdmins() {
		return userRepository.findByType(UserType.ADMIN);
	}

	@Override
	public Iterable<UserApp> findAllOperators() {
		return userRepository.findByType(UserType.OPERATOR);
	}

	@Override
	public UserType[] getTypes() {
		return UserType.values();
	}

}
