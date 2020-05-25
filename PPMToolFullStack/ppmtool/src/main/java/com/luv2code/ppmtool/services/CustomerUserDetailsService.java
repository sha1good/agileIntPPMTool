package com.luv2code.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.ppmtool.domain.User;
import com.luv2code.ppmtool.repositories.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String  username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found !");
		}
		return user;
	}

	@Transactional
	public User loadUserbyId(Long id) {
		User user = userRepository.getById(id);
		if(user ==null) {
			throw new UsernameNotFoundException("User with '" + id +"' Not Found");
		}
		
		return user;
	}
	
}
