package com.heymilo.identity.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.heymilo.identity.UserDAO;
import com.heymilo.identity.SocialType;
import com.heymilo.identity.entity.User;
import com.heymilo.identity.entity.UserRoles;
import com.heymilo.identity.entity.UserStatus;



@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	private EntityManager em;
	
	private DateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
	
	
	@Override
	public User load(Long id) {
		try{
			Query query = em.createQuery("SELECT a from User a WHERE a.id = :id");
			query.setParameter("id", id);
			return (User)query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public User findByLoginId(String loginId) {
		try{
			Query query = em.createQuery("SELECT a from User a WHERE a.loginId = :loginId");
			query.setParameter("loginId", loginId);
			query.setHint("org.hibernate.cacheable", true);
			return (User)query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public List<User> findByName(String name) {
		try{
			Query query = em.createQuery("SELECT a from User a WHERE a.name = :name");
			query.setParameter("name", name);
			query.setHint("org.hibernate.cacheable", true);
			return query.getResultList();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public User findBySocialId(SocialType type, String socialId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser(User user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		em.persist(user);
	}

	@Override
	public void addRoleToUser(User user, String roleName) {
		Set<UserRoles> roles = user.getRoles();
		if(roles == null){
			roles = new HashSet<UserRoles>();
			user.setRoles(roles);
		}
		boolean needAdd = true;
		
		for(UserRoles role : roles){
			if(role.getRoleName().equals(roleName)){
				needAdd = false;
				break;
			}
		}
		
		if(needAdd){
			UserRoles newRole = new UserRoles();
			newRole.setUser(user);
			newRole.setRoleName(roleName);
			roles.add(newRole);
		}
	}

	@Override
	public void replaceRoleToUser(User user, String oldRole, String newRoleName) {
		Set<UserRoles> roles = user.getRoles();
		if(roles == null){
			roles = new HashSet<UserRoles>();
			user.setRoles(roles);
		}
		boolean needAdd = true;
		
		Iterator<UserRoles> iter = roles.iterator();
		
		while(iter.hasNext()){
			UserRoles role = iter.next();
			if(role.getRoleName().equals(oldRole))
				iter.remove();
		}
		
		UserRoles newRole = new UserRoles();
		newRole.setUser(user);
		newRole.setRoleName(newRoleName);
		roles.add(newRole);
		
		em.merge(user);
	}

	@Override
	public void updateUser(User user) {
		user.setUpdated(new Date());
		em.merge(user);
	}

	@Override
	public User loadUser(String userId) {
		return em.getReference(User.class, userId);
	}

	@Override
	public List<User> getUsers(String role) {
		String queryString = "select userRoles.user from UserRoles as userRoles where userRoles.roleName = :role";
		Query query = em.createQuery(queryString);
		query.setParameter("role", role);
		return (List<User>)query.getResultList();
	}

	@Override
	public List<User> searchUsers(String keyword,UserStatus status, int start, int limits) {
		StringBuffer sql = new StringBuffer("FROM User a WHERE a.active = :active AND a.adminUser = :adminUser ");
		
		if(keyword != null){
			sql.append("AND (a.name = :name OR a.loginId = :loginId) ");
		}
		if(status != null){
			sql.append("AND a.status = :status ");
		}
		sql.append("Order by a.name asc");
		
		Query query = em.createQuery(sql.toString());
		query.setParameter("active", true);
		query.setParameter("adminUser",false);
		if(keyword != null){
			query.setParameter("name", keyword);
			query.setParameter("loginId", keyword);
		}	
		if(status != null){
			query.setParameter("status", status);
		}
		query.setFirstResult(start);
		query.setMaxResults(limits);
		
		query.setHint("org.hibernate.cacheable", true);
		
		return query.getResultList();
	}

	@Override
	public int countUsers(String keyword, UserStatus status) {
		StringBuffer sql = new StringBuffer("SELECT count(*) FROM User a WHERE a.active = :active AND a.adminUser = :adminUser ");
		
		if(keyword != null && !"".equals(keyword)){
			sql.append("AND (a.name = :name OR a.loginId = :loginId) ");
		}
		if(status != null){
			sql.append("AND a.status = :status ");
		}
		
		
		Query query = em.createQuery(sql.toString());
		query.setParameter("active", true);
		query.setParameter("adminUser",false);
		if(keyword != null  && !"".equals(keyword)){
			query.setParameter("name", keyword);
			query.setParameter("loginId", keyword);
		}	
		if(status != null){
			query.setParameter("status", status);
		}
		
		return ((Number)query.getSingleResult()).intValue();
	}

	
	
}
