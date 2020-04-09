package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
	public void registerUser(User user);
	public boolean checkExistUserId(String userId);
	public boolean checkPasswordByUserId(String userId, String userPassword);

	
}
