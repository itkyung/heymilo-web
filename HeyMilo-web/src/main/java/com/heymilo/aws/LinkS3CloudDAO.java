package com.heymilo.aws;

import java.util.List;

public interface LinkS3CloudDAO {
	
	void save(LinkS3ToCloudDistribution entity);
	void update(LinkS3ToCloudDistribution entity);
	List<LinkS3ToCloudDistribution> findByBucketName(String bucketName);
	
}
