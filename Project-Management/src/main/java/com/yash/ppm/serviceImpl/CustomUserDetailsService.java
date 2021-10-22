package com.yash.ppm.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.ppm.model.User;
import com.yash.ppm.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("User Name Not Found"+username);
		}
		return user;
	}
	
	
	@Transactional
	public User loadByUserId(Long id) {
		return userRepository.findById(id).get();
		
	}

	
}
