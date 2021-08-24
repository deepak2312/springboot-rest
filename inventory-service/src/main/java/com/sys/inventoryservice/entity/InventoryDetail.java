package com.sys.inventoryservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="inventory_product_status")
@Getter
@Setter
public class InventoryDetail {
	
	@Column(name="product_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	@Column(name="item_count")
	private int itemCounts;
	public InventoryDetail(int productId, int itemCounts) {
		super();
		this.productId = productId;
		this.itemCounts = itemCounts;
	}
	public InventoryDetail() {
		super();
	}	
}
