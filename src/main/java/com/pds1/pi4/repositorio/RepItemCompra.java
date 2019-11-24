package com.pds1.pi4.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.pds1.pi4.entidades.ItemCompra;

public interface RepItemCompra extends JpaRepository<ItemCompra, Long>{
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM item_compra WHERE conferido = 'N' ", nativeQuery = true)
	List<ItemCompra> findItemCompraNaoConferido();

}
