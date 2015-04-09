package com.heymilo.common.impl;

public class S3ObjectInfo {
	private String bucketName;
	private String key;
	private String accessUrl;
	
	
	public S3ObjectInfo(String bucketName, String key, String domainName) {
		super();
		this.bucketName = bucketName;
		this.key = key;
		this.accessUrl = "http://" + domainName + "/" + key;
	}
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getAccessUrl() {
		return accessUrl;
	}
	
	
}
