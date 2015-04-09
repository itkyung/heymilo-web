package com.heymilo.identity.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.heymilo.identity.UserService;


public class SignedTokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	private UserDetailsService userDetailsService;
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> authentication) {
		return (SignedAuthenticationToken.class.isAssignableFrom(authentication));
	}

	//해당 authentication으로부터 해당 user의 token을 이용할수 있는지 검증을 한다.
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		String loginId = userDetails.getUsername();
		
		SignedAuthenticationToken aut = (SignedAuthenticationToken)authentication;
		if(!userService.isValidToken(loginId, aut.getSignedToken())){
			throw new BadCredentialsException("Login Token is not valid");
		}

	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		return this.userDetailsService.loadUserByUsername(username);
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
