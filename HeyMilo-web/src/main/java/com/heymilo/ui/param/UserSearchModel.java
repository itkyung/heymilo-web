package com.heymilo.ui.param;

public class UserSearchModel extends DataTableSearchModel {
	private String keyword;
	private String status;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
