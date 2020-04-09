package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserJpo;
import com.example.demo.util.SecurityUtil;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository implements UserRepositoryStore {

	
	private UserJpaRepository userJpaRepository;
	
	public UserRepository(UserJpaRepository userJpaRepository) {
		this.userJpaRepository=userJpaRepository;
	}
	
	@Override
	public void createUser(User user) {
		
		
		SecurityUtil su = new SecurityUtil();
		String securityPassword =su.encryptSHA256(user.getUserPassword());		
		user.setUserPassword(securityPassword);
		
		UserJpo userJpo = new UserJpo(user);
		userJpaRepository.save(userJpo);
		
	}

	@Override
	public boolean checkExistUserId(String userId) {

		Optional<UserJpo> userJpo = userJpaRepository.findById(userId);
		if(userJpo.isPresent()) {
			return true; 
		}
		return false;
	}

	@Override
	public boolean checkPasswordByUserId(String userId, String userPassword) {
		SecurityUtil su = new SecurityUtil();
		String pageSendToServerPassword =su.encryptSHA256(userPassword);	
		
		Optional<UserJpo> userJpo = userJpaRepository.findById(userId);
		
		try {
			if(userJpo.get().getUserId().isEmpty()) {
				return false;
				
			}else {
				String checkPassword =  userJpo.get().getUserPassword();
				if(pageSendToServerPassword.equals(checkPassword)) {
					return true; 
				}
			}
		}catch (Exception e){
			e.getMessage();
		}
		
		return false;
	}
	
	

}
