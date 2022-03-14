package com.example.springdata.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.springdata.service.UserService;
import com.example.springdata.springjpa.model.User;
import com.example.springdata.springjpa.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public Page<User> getUsers(int page, int size, Sort sort){
		return userRepository.findAll(PageRequest.of(page, size, sort));
	}

	@Override
	public void save(User user) {
		this.userRepository.save(user);
	}

	@Override
	public Optional<User> findById(Long id) {
		return this.userRepository.findById(id);
	}
}
