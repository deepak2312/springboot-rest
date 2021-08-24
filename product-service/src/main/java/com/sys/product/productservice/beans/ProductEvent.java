package com.sys.product.productservice.beans;

public class ProductEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer productId;
	private Integer  quantity;
	
	public ProductEvent() {
		super();
	}
	public ProductEvent(Integer productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductEvent [productId=" + productId + ", quantity=" + quantity + "]";
	}

}
