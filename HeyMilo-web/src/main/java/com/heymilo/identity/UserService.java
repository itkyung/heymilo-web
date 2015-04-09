package com.heymilo.identity;

import java.util.List;

import javax.persistence.ManyToOne;

import com.heymilo.identity.entity.User;
import com.heymilo.identity.entity.UserStatus;


public interface UserService {
	final static String ADMIN_ID = "admin";
	final static String ADMIN_PASSWORD = "admin!@#$";
		
	void initAdmin();
	void initUser();
	User findByLoginId(String loginId);
	User findBySocialId(SocialType type,String socialId);
	List<User> getUsers(String role);
	User loadUser(Long id);
	
	User activateUser(Long id) throws Exception;
	boolean isValidToken(String loginId,String loginToken);
	
	User registUser(String loginId,String plainPassword,String name) throws Exception;
	User registUserWithFacebook(String facebookToken,String email,String name) throws Exception;
	
	List<UserDTO> searchUsers(String keyword, UserStatus status, int start, int limits);
	int countUsers(String keyword, UserStatus status);
}
