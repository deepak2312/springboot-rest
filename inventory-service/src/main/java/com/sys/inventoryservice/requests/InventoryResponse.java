package com.sys.inventoryservice.requests;

import java.time.LocalDate;

public class InventoryResponse {

	private Long productId;
	private Integer quantity;
	private LocalDate additionDate;
	private String updateBy;
	
	public InventoryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InventoryResponse(Long productId, Integer quantity, LocalDate additionDate, String updateBy) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.additionDate = additionDate;
		this.updateBy = updateBy;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public LocalDate getAdditionDate() {
		return additionDate;
	}
	public void setAdditionDate(LocalDate additionDate) {
		this.additionDate = additionDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	@Override
	public String toString() {
		return "InventoryRequest [productId=" + productId + ", quantity=" + quantity + ", additionDate=" + additionDate
				+ ", updateBy=" + updateBy + "]";
	}
	
	
	
}
