package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleQuantitiesDTO;
import com.devsuperior.dsmeta.entities.Sale;



public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query(value = 
			  "SELECT obj "
			+ "FROM Sale obj JOIN FETCH obj.seller "
			+ "WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%',:name,'%')) and obj.date BETWEEN :minDate AND :maxDate ",
		   countQuery = 
		      "SELECT count(obj) "
		    + "FROM Sale obj JOIN obj.seller "
		    + "WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%',:name,'%')) and obj.date BETWEEN :minDate AND :maxDate")
	Page<Sale> searchSalesByDateRange(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);
	
	
	@Query(value = 
			  "SELECT new com.devsuperior.dsmeta.dto.SaleQuantitiesDTO(obj.seller.name as ´sellerName´, SUM(obj.amount)) "
			+ "FROM Sale obj "
			+ "WHERE obj.date BETWEEN :minDate AND :maxDate "
			+ "GROUP BY obj.seller.name ")
	Page<SaleQuantitiesDTO> searchQuantitiesSalesByDateRange(LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
