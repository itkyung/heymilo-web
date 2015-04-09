package com.heymilo.shop.product.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.heymilo.shop.entity.Category;
import com.heymilo.shop.entity.Exhibition;
import com.heymilo.shop.entity.ExhibitionType;
import com.heymilo.shop.entity.Product;
import com.heymilo.shop.entity.ProductFeature;
import com.heymilo.shop.entity.ProductImage;
import com.heymilo.shop.entity.ProductStatus;
import com.heymilo.shop.master.CategoryDAO;
import com.heymilo.shop.master.ExhibitionDAO;
import com.heymilo.shop.master.ProductFeatureDAO;
import com.heymilo.shop.product.ProductDAO;
import com.heymilo.shop.product.ProductService;
import com.heymilo.ui.param.ProductSaveModel;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO dao;
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@Autowired
	private ProductFeatureDAO featureDao;
	
	@Autowired
	private ExhibitionDAO exhibitionDao;
	
	
	@Override
	public List<Product> searchProduct(String keyword, Boolean newProduct,
			Boolean canSubscription, ProductStatus status, Category category,
			int start, int limits) {
		return dao.searchProduct(keyword, newProduct, canSubscription, status, category, start, limits);
	}

	@Override
	public int countProduct(String keyword, Boolean newProduct,
			Boolean canSubscription, ProductStatus status, Category category) {
		return dao.countProduct(keyword, newProduct, canSubscription, status, category);
	}

	@Override
	public Product load(Long id) {
		return dao.load(id);
	}

	/**
	 * 제품정보를 업데이트한다.
	 */
	@Transactional
	@Override
	public Product saveProduct(ProductSaveModel productModel) {
		
		Product product = null;
		if(productModel.getProductId() != null && !productModel.getProductId().equals(0)){
			product = dao.load(productModel.getProductId());
		}else{
			product = new Product();
			product.setSalesCount(0);
		}
		
		product.setName(productModel.getName());
		product.setProductCode(productModel.getProductCode());
		product.setProductPrice(new BigDecimal(productModel.getProductPrice()));
		product.setMiloPrice(new BigDecimal(productModel.getMiloPrice()));
		product.setTotalCount(productModel.getTotalCount());
		product.setStatus(ProductStatus.valueOf(productModel.getStatus()));
		product.setProductPoint(0D);
		
		product.setBriefDesc(productModel.getBriefDesc());
		product.setHtmlDesc(productModel.getHtmlDesc());
		product.setPlainDesc(productModel.getHtmlDesc().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
		product.setMainImagePath(productModel.getMainImagePath());
		product.setCanSubscription(productModel.getCanSubscription() == null ? false : productModel.getCanSubscription());
		
		setCategory(product, productModel.getCategory());
		setFeature(product, productModel.getFeature());
		setExhibitions(product, productModel.getExhibition());
		setImages(product, productModel.getImages());
		
		if(productModel.getProductId() != null && !productModel.getProductId().equals(0)){
			dao.update(product);
		}else{
			dao.save(product);
		}
		
		return product;
	}

	private void setCategory(Product product, Long[] categories) {
		Set<Category> categoryEntities = product.getCategories();
		if(categoryEntities == null) {
			categoryEntities = Sets.newHashSet();
			product.setCategories(categoryEntities);
		}
		for(Long categoryId : categories) {
			Category category = categoryDao.loadCategory(categoryId);
			categoryEntities.add(category);
		}
	}
	
	private void setFeature(Product product, Long[] features) {
		Set<ProductFeature> featureEntities = product.getFeatures();
		if(featureEntities == null) {
			featureEntities = Sets.newHashSet();
			product.setFeatures(featureEntities);
		}
		for(Long featureId : features) {
			ProductFeature feature = featureDao.load(featureId);
			featureEntities.add(feature);
		}
	}
	
	private void setExhibitions(Product product, Long[] exhibitions) {
		product.setNewProduct(false);
		if(exhibitions.length == 0) return;
		
		Set<Exhibition> exhibitionEntities = product.getExhibitions();
		if(exhibitionEntities == null) {
			exhibitionEntities = Sets.newHashSet();
			product.setExhibitions(exhibitionEntities);
		}
		for(Long exhibitionId : exhibitions) {
			Exhibition exhibition = exhibitionDao.load(exhibitionId);
			exhibitionEntities.add(exhibition);
			if(exhibition.getType().equals(ExhibitionType.NEW_PRODUCT)){
				product.setNewProduct(true);
			}
		}
	}
	
	private void setImages(Product product, String[] images) {
		List<ProductImage> subImages = product.getImages();
		if(subImages == null) {
			subImages = Lists.newArrayList();
			product.setImages(subImages);
		}else{
			subImages.clear();
		}
		
		for(String imagePath : images) {
			ProductImage productImage = new ProductImage();
			productImage.setImagePath(imagePath);
			productImage.setCreated(new Date());	
			productImage.setProduct(product);
			subImages.add(productImage);
		}
		
	}
}
