package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SaleMinDTO {

	private Long id;
	private LocalDate date;
	private Double amount;
	// private Seller seller;
	private String sellerName;
	
	public SaleMinDTO() {
		
	}
	
	public SaleMinDTO(Long id, Double amount, LocalDate date, Seller seller) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		sellerName = seller.getName();
	}
	
	public SaleMinDTO(Sale entity) {
		id = entity.getId();
		date = entity.getDate();
		amount = entity.getAmount();
		sellerName = entity.getSeller().getName();
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
}
