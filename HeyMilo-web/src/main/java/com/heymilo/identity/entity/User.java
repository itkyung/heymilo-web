package com.heymilo.identity.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name=User.TABLE_NAME)
public class User {
	public static final String TABLE_NAME = "HM_USER";
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=128)
	private String name;
	
	@Column(length=128)
	private String email;
	
	@Column(length=128)
	private String loginId;
	
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginDate;
	
	private boolean active;
	
	private String facebook;
	
	private String twitter;
	
	@Column(length=128)
	private String loginToken;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenExpireDate;	//loginToken만료일 - 한달뒤로 설정. 
	
	
	@OneToMany(mappedBy="user",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private Set<UserRoles> roles;
	
	private boolean adminUser = false;
	
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getUpdated() {
		return updated;
	}


	public void setUpdated(Date updated) {
		this.updated = updated;
	}


	public Date getLastLoginDate() {
		return lastLoginDate;
	}


	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String getFacebook() {
		return facebook;
	}


	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}


	public String getTwitter() {
		return twitter;
	}


	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}


	public String getLoginToken() {
		return loginToken;
	}


	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}


	public Date getTokenExpireDate() {
		return tokenExpireDate;
	}


	public void setTokenExpireDate(Date tokenExpireDate) {
		this.tokenExpireDate = tokenExpireDate;
	}


	public Set<UserRoles> getRoles() {
		return roles;
	}


	public void setRoles(Set<UserRoles> roles) {
		this.roles = roles;
	}


	public UserStatus getStatus() {
		return status;
	}


	public void setStatus(UserStatus status) {
		this.status = status;
	}


	public boolean isAdminUser() {
		return adminUser;
	}


	public void setAdminUser(boolean adminUser) {
		this.adminUser = adminUser;
	}
	
	
}
