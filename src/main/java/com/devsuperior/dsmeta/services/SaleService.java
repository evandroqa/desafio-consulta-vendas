package com.devsuperior.dsmeta.services;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
    LocalDate ldFinal; 
    LocalDate ldInicio;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	 
	@Transactional(readOnly = true)
	public Page<SaleMinDTO> findPaginado(String minDate, String maxDate, String name, Pageable pageable) {

        if (maxDate.equals("")) {
        	ldFinal = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			ldFinal = LocalDate.parse(maxDate);
		}
        if (minDate.equals("")) {
        	ldInicio = ldFinal.minusYears(1L);
        } else {
        	ldInicio = LocalDate.parse(minDate);
        }

		return repository.searchPaginado(ldInicio, ldFinal, name, pageable).map(x -> new SaleMinDTO(x));
	}
}
