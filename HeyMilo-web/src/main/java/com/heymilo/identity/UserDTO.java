package com.heymilo.identity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.google.gson.annotations.Expose;
import com.heymilo.identity.entity.User;

public class UserDTO {
	@Expose
	private Long id;
	@Expose
	private String loginId;
	@Expose
	private String name;
	@Expose
	private String status;
	@Expose
	private String createdAtStr;
	@Expose
	private String lastLoginDateStr;
	
	private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH24:mm");
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.loginId = user.getLoginId();
		this.name = user.getName();
		this.status = user.getStatus().name();
		this.createdAtStr = format.format(user.getCreated());
		if(user.getLastLoginDate()  != null) {
			this.lastLoginDateStr = format.format(user.getLastLoginDate());
		}else{
			this.lastLoginDateStr = "";
		}
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedAtStr() {
		return createdAtStr;
	}

	public void setCreatedAtStr(String createdAtStr) {
		this.createdAtStr = createdAtStr;
	}

	public String getLastLoginDateStr() {
		return lastLoginDateStr;
	}

	public void setLastLoginDateStr(String lastLoginDateStr) {
		this.lastLoginDateStr = lastLoginDateStr;
	}
	
	
}
