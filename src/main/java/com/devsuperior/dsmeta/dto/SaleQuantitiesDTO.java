package com.devsuperior.dsmeta.dto;

public class SaleQuantitiesDTO {

	private String sellerName;
	private Double total;

	public SaleQuantitiesDTO() {
	}
	
	public SaleQuantitiesDTO(String sellerName, Double total) {
		this.sellerName = sellerName;
		this.total = total;
	}

	public String getSellerName() {
		return sellerName;
	}

	public Double getTotal() {
		return this.total;
	}
}
