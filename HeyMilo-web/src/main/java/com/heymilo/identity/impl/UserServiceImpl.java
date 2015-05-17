package com.heymilo.identity.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;
import com.heymilo.common.CommonUtils;
import com.heymilo.external.IMailService;
import com.heymilo.identity.ILogin;
import com.heymilo.identity.UserDAO;
import com.heymilo.identity.UserDTO;
import com.heymilo.identity.UserService;
import com.heymilo.identity.SocialType;
import com.heymilo.identity.entity.AddressBook;
import com.heymilo.identity.entity.Role;
import com.heymilo.identity.entity.User;
import com.heymilo.identity.entity.UserRoles;
import com.heymilo.identity.entity.UserStatus;

@Service("UserService")
public class UserServiceImpl implements ILogin,UserService {
	@Autowired UserDAO dao;
	
	@Autowired IMailService mailService;
	
	Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Transactional(readOnly=true)
	@Override
	public User getCurrentUser() {
		if(SecurityContextHolder.getContext().getAuthentication() == null) return null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal != null){
			if(principal instanceof String){
				return null; //익명의 사용자이다.
			}
			org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User)principal;
			User user = dao.findByLoginId(springUser.getUsername());
			return user;
		}
		return null;
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isInRole(User user, String roleName) {
		for(UserRoles ur : user.getRoles()){
			if(ur.getRoleName().equals(roleName))
				return true;
		}
		return false;
	}

	@Transactional
	@Override
	public void updateLastLoginDate(User user) {
		user.setLastLoginDate(new Date());
		dao.updateUser(user);
	}

	@Transactional
	@Override
	public void updateLoginData(User user, Date lastLoginDate) {
		user.setLastLoginDate(lastLoginDate);
	

		//여기에서 loginToken도 새로 발행해준다.
		user.setLoginToken(makeLoginToken(user));
		Calendar cur = Calendar.getInstance();
		cur.add(Calendar.MONTH, 1);	//우선 한달뒤로 설정한다. 
		user.setTokenExpireDate(cur.getTime());
		
		dao.updateUser(user);
	}
	
	private String makeLoginToken(User user){
		Date curr = new Date();
		return user.getId() + "-" + curr.getTime();
	}
	
	@Transactional
	@Override
	public void initAdmin() {
		try{
			User adminUser = dao.findByLoginId(UserService.ADMIN_ID);
			if(adminUser == null){
				adminUser = new User();
				adminUser.setLoginId(UserService.ADMIN_ID);	
				adminUser.setPassword(new String(CommonUtils.md5(UserService.ADMIN_PASSWORD)));
				adminUser.setName("admin");
				adminUser.setActive(true);
				adminUser.setStatus(UserStatus.ACTIVE);
				adminUser.setCreated(new Date());
				
				dao.addRoleToUser(adminUser,Role.ADMIN_ROLE);
				dao.createUser(adminUser);
			}
		}catch(Exception e){
			log.error("Init admin error : " + e);
		}
		
	}

	@Override
	public void initUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findByLoginId(String loginId) {
		return dao.findByLoginId(loginId);
	}

	@Override
	public User findBySocialId(SocialType type, String socialId) {
		return dao.findBySocialId(type, socialId);
	}

	@Override
	public List<User> getUsers(String role) {
		return dao.getUsers(role);
	}

	@Override
	public User loadUser(Long id) {
		return dao.load(id);
	}

	@Override
	public boolean isValidToken(String loginId, String loginToken) {
		User user = findByLoginId(loginId);
		if(user != null){
			Date currDate = new Date();
			if(loginToken.equals(user.getLoginToken()) && user.getTokenExpireDate() != null 
					&& user.getTokenExpireDate().after(currDate)){
				return true;
			}
		}
		
		return false;
	}

	
	@Transactional
	@Override
	public User registUser(String loginId, String plainPassword, String name)  throws Exception{
		User user = dao.findByLoginId(loginId);
		if(user != null){
			throw new Exception("이미 존재하는 로그인 아이디입니다.");
		}
		
		user = new User();
		user.setLoginId(loginId);
		user.setName(name);
		user.setPassword(new String(CommonUtils.md5(plainPassword)));
		user.setActive(true);
		user.setStatus(UserStatus.WAITING);
		
		dao.addRoleToUser(user,Role.USER_ROLE);
	
		dao.createUser(user);
		
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("id", user.getId());
		vars.put("name", user.getName());
		mailService.sendEmail(user.getLoginId(), "헤이마일로 인증메일", "verify", vars);
		
		return user;
	}

	@Transactional
	@Override
	public User registUserWithFacebook(String facebookToken, String email,
			String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public User activateUser(Long id) throws Exception {
		User user = dao.load(id);
		if(user == null){
			throw new Exception("해당하는 사용자가 존재하지 않습니다.");
		}
		
		user.setStatus(UserStatus.ACTIVE);
		user.setUpdated(new Date());
		dao.updateUser(user);
		
		return user;
	}

	@Override
	public List<UserDTO> searchUsers(String keyword, UserStatus status, int start, int limits) {
		List<UserDTO> results = Lists.newArrayList();
		List<User> users = dao.searchUsers(keyword,status,start, limits);
		
		for(User user : users){
			UserDTO dto = new UserDTO(user);
			results.add(dto);
		}
		
		return results;
	}

	@Override
	public int countUsers(String keyword, UserStatus status) {
		return dao.countUsers(keyword, status);
	}

	
	@Override
	public AddressBook findUserActiveAddress(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
