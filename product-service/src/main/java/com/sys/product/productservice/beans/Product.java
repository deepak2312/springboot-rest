package com.sys.product.productservice.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="product_service")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	@Column(name="name")
	private String productName;
	@Column(name="detail")
	private String productDetail;
	@Column(name="status")
	private String  status;
	
	
	
	public Product() {
		super();
	}
	
	public Product(String productName, String productDetail, String status) {
		super();
		this.productName = productName;
		this.productDetail = productDetail;
		this.status = status;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDetail=" + productDetail
				+ ", status=" + status + "]";
	}

	
	
	
}
