package com.heymilo.common.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jets3t.service.CloudFrontService;
import org.jets3t.service.CloudFrontServiceException;
import org.jets3t.service.model.cloudfront.Distribution;
import org.jets3t.service.model.cloudfront.S3Origin;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.heymilo.aws.LinkS3CloudDAO;
import com.heymilo.aws.LinkS3ToCloudDistribution;
import com.heymilo.common.FileService;
import com.heymilo.common.ImageInfo;
import com.heymilo.identity.entity.User;

@Service
public class AWSFileServiceImpl implements FileService {
	
	@Value("${aws.access.key.id}")
	private String awsAccessKeyId;
	
	@Value("${aws.secret.access.key}")
	private String awsSecretAccessKey;
	
	@Autowired
	private LinkS3CloudDAO linkS3CloudDao;
	
	private static final String BUCKET_POSTFIX = ".s3.heymilo.com";
	
	private static Logger log = Logger.getLogger(AWSFileServiceImpl.class);
	
	@Transactional
	@Override
	public ImageInfo updateImage(MultipartFile file, String fileGroup) throws IOException {
		
		S3ObjectInfo s3Object = saveToS3(fileGroup, file.getOriginalFilename(), file.getInputStream(), file.getContentType());
		
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setSuccess(true);
		imageInfo.setImagePath(s3Object.getAccessUrl());
		
		return imageInfo;
	}

	/**
	 * 해당 파일을 AWS S3 SDK를 이용해서 S3에 저장한다.
	 * S3 버킷이 이미 존재하면 이미 연결되 Distribution정보를 얻어오고
	 * 그렇지 않으면 버킷을 만들고 그것과 연결되 Distribution정보를 얻어와서 저장시킨다.
	 * 그리고 그 하위에 해당하는 file을 S3 버킷밑에 저장하고 
	 * @param fileGroup
	 * @param fileName
	 * @param inputStream
	 * @return
	 */
	private S3ObjectInfo saveToS3(String fileGroup, String fileName, InputStream inputStream,  String contentType) {
		try{
			BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
			AmazonS3 s3Client = new AmazonS3Client(awsCreds);
			
			Region southEast1 = Region.getRegion(Regions.AP_SOUTHEAST_1);
			s3Client.setRegion(southEast1);
			
			String bucketName = fileGroup + BUCKET_POSTFIX;
			String objectKey = UUID.randomUUID().toString() + "_" + fileName.toLowerCase();
			
			Distribution cloudDistribution = null;
			if(!existBucket(s3Client, bucketName)) {
				
				s3Client.createBucket(bucketName);
				s3Client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
				//이렇게 한번 CloudFront Distribution을 만들면 바로 적용이 되지 않는다.
				//그렇기 때문에 이부분은 이렇게 테스트로 한번 만들도 이후에는 계속 동일한곳에 S3만 만들어질수 있도록 처리하는게 좋을듯하다.
				cloudDistribution = createCloudFrontDistribution(bucketName);
				saveLinkData(bucketName, cloudDistribution.getId());
			}else{
				//해당 Bucket에 연결되는 CloudFront Distribution정보를 얻어온다.
				LinkS3ToCloudDistribution distributionLink = findDistributionInfo(bucketName);
				cloudDistribution = getCloudFrontDistribution(distributionLink.getDistributionId());
			}
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);
			
			//s3에 해당 파일을 업로드한다.
			s3Client.putObject(new PutObjectRequest(bucketName, objectKey, inputStream,metadata).withCannedAcl(CannedAccessControlList.PublicRead));
			
			return new S3ObjectInfo(bucketName, objectKey, cloudDistribution.getDomainName());
		} catch(AmazonServiceException e) {
			log.error("S3",e);
		} catch(AmazonClientException ce) {
			log.error("S3",ce);
		} catch(CloudFrontServiceException cse) {
			log.error("CloudFront",cse);
		}
		
		return new S3ObjectInfo(null, null, null);
	}
	
	private void saveLinkData(String bucketName, String distributionId) {
		LinkS3ToCloudDistribution entity = new LinkS3ToCloudDistribution();
		entity.setBucketName(bucketName);
		entity.setDistributionId(distributionId);
		linkS3CloudDao.save(entity);
	}
	
	private LinkS3ToCloudDistribution findDistributionInfo(String bucketName){
		List<LinkS3ToCloudDistribution> datas = linkS3CloudDao.findByBucketName(bucketName);
		if(datas.size() > 0){
			return datas.get(0);
		}else{
			return null;
		}
	}
	
	private boolean existBucket(AmazonS3 s3Client, String bucketName) {
		try {
			s3Client.getBucketLocation(bucketName);
			return true;
		} catch(AmazonServiceException e) {
			return false;
		}
	}
	
	/**
	 * S3의 Bucket에 연결되는 CloudFront WebDistribution을 만든다.
	 * 이때에 JetS3t SDK를 이용해서 처리한다.
	 * 
	 * @param s3Object
	 */
	private Distribution createCloudFrontDistribution(String bucketName) throws CloudFrontServiceException{
		CloudFrontService cloudFrontService = new CloudFrontService(new AWSCredentials(awsAccessKeyId, awsSecretAccessKey));
		
		Distribution newDistribution = cloudFrontService.createDistribution(
				new S3Origin(bucketName),
	            "" + System.currentTimeMillis(), // Caller reference - a unique string value
	            new String[] {}, // CNAME aliases for distribution
	            "HeyMilo", // Comment
	            true,  // Distribution is enabled?
	            null  // Logging status of distribution (null means disabled)
	            );
		
		return newDistribution;
	}
	
	private Distribution getCloudFrontDistribution(String distributionId) throws CloudFrontServiceException{
		CloudFrontService cloudFrontService = new CloudFrontService(new AWSCredentials(awsAccessKeyId, awsSecretAccessKey));
		
		return cloudFrontService.getDistributionInfo(distributionId);
	}
}
