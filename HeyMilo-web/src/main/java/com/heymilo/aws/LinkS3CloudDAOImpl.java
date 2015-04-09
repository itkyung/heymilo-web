package com.heymilo.aws;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class LinkS3CloudDAOImpl implements LinkS3CloudDAO {
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	private EntityManager em;
	
	
	@Override
	public void save(LinkS3ToCloudDistribution entity) {
		entity.setCreated(new Date());
		em.persist(entity);
	}

	@Override
	public void update(LinkS3ToCloudDistribution entity) {
		em.merge(entity);
	}

	@Override
	public List<LinkS3ToCloudDistribution> findByBucketName(String bucketName) {
		String sql = "FROM LinkS3ToCloudDistribution a WHERE a.bucketName = :bucketName";
		
		Query query = em.createQuery(sql);
		query.setParameter("bucketName", bucketName);
		query.setHint("org.hibernate.cacheable", true);
		
		return query.getResultList();
	}

}
