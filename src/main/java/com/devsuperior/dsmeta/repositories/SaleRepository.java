package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
	Page<Sale> searchPaginado(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);
	
}
