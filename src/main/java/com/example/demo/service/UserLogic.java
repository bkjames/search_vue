package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepositoryStore;

import java.util.ArrayList;

public class UserLogic implements UserService {
	
	private UserRepositoryStore userRepositoryStore;
	
	public UserLogic(UserRepositoryStore userRepositoryStore) {
		this.userRepositoryStore=userRepositoryStore;
	}

	@Override
	public void registerUser(User user) {
		
		user.setHistories(new ArrayList<>());
		userRepositoryStore.createUser(user);
	}

	@Override
	public boolean checkExistUserId(String userId) {

		
		return userRepositoryStore.checkExistUserId(userId);
	}

	@Override
	public boolean checkPasswordByUserId(String userId, String userPassword) {

		return userRepositoryStore.checkPasswordByUserId(userId,userPassword);
	}
	
	
	

}
