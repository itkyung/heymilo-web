package com.heymilo.ui.param;

public class ProductSaveModel {
	
	private Long productId;
	private Long[] category = new Long[30];
	private Long[] feature = new Long[30];
	private Long[] exhibition = new Long[30];
	private String status;
	private String name;
	private String productCode;
	private String dueDate;
	
	private String mainImagePath;
	private String[] images = new String[10];
	private String briefDesc;
	private String htmlDesc;
	private double productPrice;
	private double miloPrice;
	private int totalCount;
	private Boolean canSubscription;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long[] getCategory() {
		return category;
	}
	public void setCategory(Long[] category) {
		this.category = category;
	}
	public Long[] getFeature() {
		return feature;
	}
	public void setFeature(Long[] feature) {
		this.feature = feature;
	}
	public Long[] getExhibition() {
		return exhibition;
	}
	public void setExhibition(Long[] exhibition) {
		this.exhibition = exhibition;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getMainImagePath() {
		return mainImagePath;
	}
	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
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
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public double getMiloPrice() {
		return miloPrice;
	}
	public void setMiloPrice(double miloPrice) {
		this.miloPrice = miloPrice;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public Boolean getCanSubscription() {
		return canSubscription;
	}
	public void setCanSubscription(Boolean canSubscription) {
		this.canSubscription = canSubscription;
	}
	
	
}
