package com.ecom.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.LoginRequest;
import com.ecom.dto.RegisterRequest;
import com.ecom.entity.User;
import com.ecom.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final PasswordEncoder passwordEncoder;
	private final UserServiceImpl userService;

	@Autowired
	public AuthController(PasswordEncoder passwordEncoder, UserServiceImpl userService) {
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest, BindingResult result) {
		if (result.hasErrors()) {
			// Collect all validation messages
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errors);
		}
		if (userService.userExists(registerRequest.getEmail())) {
			return ResponseEntity.badRequest().body("User Already Exists");
		}
		userService.registerUser(registerRequest);
		return ResponseEntity.ok("User Registered Successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
		if (result.hasErrors()) {
			// Collect all validation messages
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errors);
		}
		try {
			User user = userService.getUserByEmail(loginRequest.getEmail());
			if (!userService.passwordMatched(loginRequest.getPassword(), user.getPassword())) {
				return ResponseEntity.status(401).body("Invalid credentials");
			}
			Map<String, Object> userData = new HashMap<>();
			userData.put("id", user.getId());
			userData.put("name", user.getFirstName() + " " + user.getLastName());
			userData.put("email", user.getEmail());
			userData.put("phone", user.getPhone());
			userData.put("profileImageURL", user.getProfileImageURL());
			userData.put("address", user.getAddress());
			return ResponseEntity.ok(userData + " Login Successfull");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Unknow Error Occurred");
		}
	}
}
