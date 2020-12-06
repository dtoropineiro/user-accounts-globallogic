package com.dario.useraccounts.service;

import java.util.ArrayList;

import com.dario.useraccounts.exceptions.PermissionDeniedException;
import com.dario.useraccounts.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	public JwtUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("dario".equals(username)) {
			return new User("dario", "$2y$12$7eFs0uqxgxhMYNWUBLAIA.sjqQP65RmmCZbw8J0Aw8/KbeTOusk8K",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	public UserDetails loadUserByJwtRequest(String username) throws PermissionDeniedException {
		if(userRepository.findByUsername(username).isPresent()){
			return new User(userRepository.findByUsername(username).get().getUsername(), userRepository.findByUsername(username).get().getUsername(),
					new ArrayList<>());
		}else {
			throw new PermissionDeniedException("Permission denied");
		}
	}
}
