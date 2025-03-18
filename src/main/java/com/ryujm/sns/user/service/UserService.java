package com.ryujm.sns.user.service;

import org.springframework.stereotype.Service;

import com.ryujm.sns.common.MD5HashingEncoder;
import com.ryujm.sns.user.domain.User;
import com.ryujm.sns.user.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, encryptPassword, name, email);
		if(count == 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean isDuplicateId(String loginId) {
		
		int count = userRepository.selectCountByLoginId(loginId);
		
		if(count == 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public User getUser(String loginId, String password) {
		
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		return userRepository.selectUser(loginId, encryptPassword);
	}
	
	
	
	
	
	
	
	
	
	
	
}
