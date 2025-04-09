package com.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.dto.AddressDTO;
import com.ecom.dto.RegisterRequest;
import com.ecom.entity.Address;
import com.ecom.entity.Role;
import com.ecom.entity.User;
import com.ecom.repo.UserRepo;

@Service
public class UserServiceImpl implements UserServiceInterface {

	private final UserRepo userRepo;
	private final PasswordEncoder encoder;

	@Autowired
	public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder) {
		this.userRepo = userRepo;
		this.encoder = encoder;
	}

	// registers a new user
	@Override
	public User registerUser(RegisterRequest dto) {
		User user = new User();
		user.setFirstName(dto.getFirstName());
		user.setMiddleName(dto.getMiddleName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setPhone(dto.getPhone());
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setProfileImageURL(dto.getProfileImageURL());
		AddressDTO a = dto.getAddress();
		Address add = new Address();
		add.setTown(a.getTown());
		add.setLocalAddress(a.getLocalAddress());
		add.setCity(a.getCity());
		add.setDistrict(a.getDistrict());
		add.setPincode(a.getPincode());
		add.setState(a.getState());
		user.setAddress(add);
		user.setRole(Role.USER);
		return userRepo.save(user);
	}

	// checks if a user exists
	@Override
	public boolean userExists(String email) {
		return userRepo.findByEmail(email).isPresent();
	}

	// get the user by its eamil aka username
	@Override
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	// matches the password with the encoded password
	@Override
	public boolean passwordMatched(String rawPassword, String encodedPassword) {
		return encoder.matches(rawPassword, encodedPassword);
	}

}
