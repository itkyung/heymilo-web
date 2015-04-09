package com.heymilo.identity;

import java.util.List;

import com.heymilo.identity.entity.User;
import com.heymilo.identity.entity.UserStatus;



public interface UserDAO {
	User load(Long id);
	User findByLoginId(String loginId);	
	List<User> findByName(String name);
	User findBySocialId(SocialType type,String socialId);
	void createUser(User user);
	void addRoleToUser(User user, String roleName);
	void replaceRoleToUser(User user,String oldRole,String newRole);
	void updateUser(User user);
	User loadUser(String userId);
	
	List<User> getUsers(String role);
	List<User> searchUsers(String keyword,UserStatus status, int start,int limits);
	int countUsers(String keyword, UserStatus status);
}
