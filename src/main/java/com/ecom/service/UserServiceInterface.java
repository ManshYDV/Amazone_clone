package com.ecom.service;

import com.ecom.dto.RegisterRequest;
import com.ecom.entity.User;

public interface UserServiceInterface {
	User registerUser(RegisterRequest dto);

	boolean userExists(String email);

	User getUserByEmail(String email);

	boolean passwordMatched(String rawPassword, String encodedPassword);
}
