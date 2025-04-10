// UserController.java
package com.ecom.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.User;
import com.ecom.service.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserServiceImpl userService;

	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@GetMapping("/profile")
	public User getProfile(Authentication authentication) {
		// DEBUGGING LINE: Log the current user's authorities
		System.out.println("Authenticated user: " + authentication.getName());
		System.out.println("Authorities: " + authentication.getAuthorities());
		String email = authentication.getName(); // from JWT subject
		return userService.getUserByEmail(email);
	}
}
