package com.example.springdata.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.example.springdata.springjpa.model.User;

public interface UserService {
	
	void save(User user);
	
	Optional<User> findById(Long id);

	Page<User> getUsers(int page, int size, Sort sort);

}
