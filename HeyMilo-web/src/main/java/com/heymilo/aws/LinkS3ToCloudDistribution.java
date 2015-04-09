package com.heymilo.aws;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

/**
 * S3의 Bucket Name과 CloudDistribution Id와의 연결 테이블 
 * @author itkyung
 *
 */
@Entity
@Table(name=LinkS3ToCloudDistribution.TABLE_NAME)
public class LinkS3ToCloudDistribution {
	public static final String TABLE_NAME = "HM_LINK_S3_DISTRIBUTION";
	
	@Expose
	@Id
	@Column(name = "LINK_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String bucketName;
	
	private String distributionId;
	
	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getDistributionId() {
		return distributionId;
	}

	public void setDistributionId(String distributionId) {
		this.distributionId = distributionId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
