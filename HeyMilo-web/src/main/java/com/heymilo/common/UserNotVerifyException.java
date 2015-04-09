package com.heymilo.common;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotVerifyException extends UsernameNotFoundException {

	public UserNotVerifyException(String msg) {
		super(msg);
		
	}

}
