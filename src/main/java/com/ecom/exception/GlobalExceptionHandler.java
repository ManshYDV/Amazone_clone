package com.ecom.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({ UsernameNotFoundException.class, BadCredentialsException.class })
	public ResponseEntity<Map<String, String>> handleAuthException(RuntimeException ex) {
		Map<String, String> error = new HashMap();
		error.put("error", "Unauthorized");
		error.put("message", ex.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
}
