package com.luv2code.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luv2code.ppmtool.domain.User;
import com.luv2code.ppmtool.exceptions.UserNameAlreadyExistException;
import com.luv2code.ppmtool.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private  UserRepository userRepository;

	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public User saveUser(User newUser) {
		
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			
			//make Sure that the Username is Unique
			newUser.setUsername(newUser.getUsername());
			//Make Sure that password and Confirm password match
			//Make Sure that we do not persist nor Show confirm password
			newUser.setConfirmPassword("");
			
			return userRepository.save(newUser);
			
		}catch (Exception e) {
			throw new UserNameAlreadyExistException("Username with '" + newUser.getUsername() +"' already exist");
		}
		

	}
}
