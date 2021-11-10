package com.epam.esm.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.esm.dto.UserDetailsImpl;
import com.epam.esm.dto.UserDto;
import com.epam.esm.request.AuthRequest;
import com.epam.esm.response.JwtResponse;
import com.epam.esm.response.UserResponse;
import com.epam.esm.security.JwtTokenUtil;
import com.epam.esm.service.UserService;

@RestController
@RequestMapping("/users")
public class AuthenticationController {
	public static Logger log = LogManager.getLogger();
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserService userService;
	
	/**
	 * Logs in user , processes POST requests at /users/login
	 *
	 * @param loginRequest {@link AuthRequest} request
	 * @return {@link ResponseEntity} login user
	 */
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> loginUser(@Valid @RequestBody AuthRequest loginRequest) {
		log.info("User {} logs in", loginRequest.getUsername());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtTokenUtil.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 roles));
	}
	
	/**
	 * Registers user , processes POST requests at /users/signup
	 *
	 * @param signUpRequest {@link AuthRequest} request
	 * @return {@link ResponseEntity} registered user
	 */
	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> registerUser(@Valid @RequestBody AuthRequest signUpRequest) {
		log.info("User {} registers", signUpRequest.getUsername());
		UserDto userDto = new UserDto();
		userDto.setLogin(signUpRequest.getUsername());
		userDto.setPassword(encoder.encode(signUpRequest.getPassword()));
		UserDto userDtoCreated = userService.create(userDto);
		return ResponseEntity.ok(UserResponse.valueOf(userDtoCreated));
	}
}
