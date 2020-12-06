package com.dario.useraccounts.controller;

import com.dario.useraccounts.exceptions.EntityNotFoundException;
import com.dario.useraccounts.config.JwtTokenUtil;
import com.dario.useraccounts.exceptions.PermissionDeniedException;
import com.dario.useraccounts.model.User;
import com.dario.useraccounts.model.JwtResponse;
import com.dario.useraccounts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	private UserService userService;

	@PostMapping(value = "/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody User authenticationRequest)
			throws Exception {

		userService.createUser(authenticationRequest);
		final String token = jwtTokenUtil.generateToken(authenticationRequest);

		return ResponseEntity.ok(JwtResponse.builder()
		.id(authenticationRequest.getUserid())
		.created(authenticationRequest.getCreated())
		.modified(authenticationRequest.getModified())
		.token(token)
		.isActive(authenticationRequest.getIsActive())
		.build());
	}

	@PutMapping(value = "/modify")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User authenticationRequest)
			throws Exception {

		userService.updateUser(authenticationRequest);
		final String token = jwtTokenUtil.generateToken(authenticationRequest);

		return ResponseEntity.ok(JwtResponse.builder()
				.id(authenticationRequest.getUserid())
				.created(authenticationRequest.getCreated())
				.modified(authenticationRequest.getModified())
				.token(token)
				.isActive(authenticationRequest.getIsActive())
				.build());
	}

	@GetMapping(value = "getuser/{username}")
	public User getByUsername(@PathVariable("username") String username)
			throws EntityNotFoundException, PermissionDeniedException {

		return userService.getUserByName(username);

	}

	@GetMapping(value = "/{id}")
	public User getById(@PathVariable("id") Long id)
			throws EntityNotFoundException {

		return userService.getUser(id);

	}

}
