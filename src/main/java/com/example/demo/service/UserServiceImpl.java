package com.example.demo.service;

import com.example.demo.repository.UserRepositoryStore;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl extends UserLogic {
	
	public UserServiceImpl(UserRepositoryStore userRepositoryStore) {
		super(userRepositoryStore);
	}
}
