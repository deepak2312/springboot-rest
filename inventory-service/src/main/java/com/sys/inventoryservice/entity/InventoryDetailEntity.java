package com.sys.inventoryservice.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="inventory_details")
public class InventoryDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="productId")
	private Long productId;
	@Column(name="quantity")
	private Integer quantity;
	@Column(name="additionDate")
	private LocalDate additionDate;
	@Column(name="updatedBy")
	private String updatedBy;
	
	public InventoryDetailEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InventoryDetailEntity(Long productId, Integer quantity, LocalDate additionDate, String updatedBy) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.additionDate = additionDate;
		this.updatedBy = updatedBy;
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
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Override
	public String toString() {
		return "InventoryDetailRequest [productId=" + productId + ", quantity=" + quantity + ", additionDate="
				+ additionDate + ", updatedBy=" + updatedBy + "]";
	}
	
	
	
	
}
