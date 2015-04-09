package com.heymilo.shop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

@Entity
@Table(name=Product.TABLE_NAME)
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8353227273404920775L;

	public static final String TABLE_NAME = "HM_PRODUCT";
	
	@Expose
	@Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Expose
	@Column(length=128)
	private String name;
	
	@Expose
	@Column(length=128)
	private String productCode;
	
	@Column(columnDefinition = "TEXT")
	private String briefDesc;
	
	@Column(columnDefinition = "TEXT")
	private String htmlDesc;
	
	@Column(columnDefinition = "TEXT")
	private String plainDesc;
	
	@Temporal(TemporalType.DATE)
	private Date dueDate;	//유효일자
	
	@Expose
	private BigDecimal productPrice;	//상품가 
	
	@Expose
	private BigDecimal miloPrice;	//마일로가 
	
	@Column(nullable=false)
	private double productPoint;	//평균평점 (총 5점제)
	
	@Expose
	@Column(length=256)
	private String mainThumbnailPath;	//메인 썸네일 이미지주소 
	
	@Expose
	@Column(length=256)
	private String mainImagePath;	//메인 이미지 주소.
	
	@Expose
	@Column(nullable=false)
	private boolean newProduct;	//신제품여부를 관리한다. 신상품 기획전에 등록되면 자동으로 업데이트되는 형태로 가야할듯함...
	
	@Expose
	@Column(nullable=false)
	private boolean canSubscription;	//정기구독가능여부. 정기구독 기획전에 등록되면 자동으로 업데이트 되는 형태로 가야함.

	@Expose
	@Enumerated(EnumType.ORDINAL)
	private ProductStatus status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("created")
	private List<ProductImage> images;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "HM_PRODUCT_CATEGORY_LINK", joinColumns = { 
			@JoinColumn(name = "PRODUCT_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "CATEGORY_ID", 
					nullable = false, updatable = false) })
	private Set<Category> categories = new HashSet<Category>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "HM_PRODUCT_EXHIBITION_LINK", joinColumns = { 
			@JoinColumn(name = "PRODUCT_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "EXHIBITION_ID", 
					nullable = false, updatable = false) })
	private Set<Exhibition> exhibitions = new HashSet<Exhibition>();
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "HM_PRODUCT_FEATURE_LINK", joinColumns = { 
			@JoinColumn(name = "PRODUCT_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "FEATURE_ID", 
					nullable = false, updatable = false) })
	private Set<ProductFeature> features = new HashSet<ProductFeature>();
	
	@Expose
	@Column(nullable=false)
	private int totalCount;	// 등록수량 
	
	@Expose
	@Column(nullable=false)
	private int salesCount; // 판매수량 
	
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

	public String getBriefDesc() {
		return briefDesc;
	}

	public void setBriefDesc(String briefDesc) {
		this.briefDesc = briefDesc;
	}

	public String getHtmlDesc() {
		return htmlDesc;
	}

	public void setHtmlDesc(String htmlDesc) {
		this.htmlDesc = htmlDesc;
	}

	public String getPlainDesc() {
		return plainDesc;
	}

	public void setPlainDesc(String plainDesc) {
		this.plainDesc = plainDesc;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public BigDecimal getMiloPrice() {
		return miloPrice;
	}

	public void setMiloPrice(BigDecimal miloPrice) {
		this.miloPrice = miloPrice;
	}

	public double getProductPoint() {
		return productPoint;
	}

	public void setProductPoint(double productPoint) {
		this.productPoint = productPoint;
	}

	public String getMainThumbnailPath() {
		return mainThumbnailPath;
	}

	public void setMainThumbnailPath(String mainThumbnailPath) {
		this.mainThumbnailPath = mainThumbnailPath;
	}

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public boolean isCanSubscription() {
		return canSubscription;
	}

	public void setCanSubscription(boolean canSubscription) {
		this.canSubscription = canSubscription;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
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

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Exhibition> getExhibitions() {
		return exhibitions;
	}

	public void setExhibitions(Set<Exhibition> exhibitions) {
		this.exhibitions = exhibitions;
	}

	public Set<ProductFeature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<ProductFeature> features) {
		this.features = features;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getMainImagePath() {
		return mainImagePath;
	}

	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	
	
}
