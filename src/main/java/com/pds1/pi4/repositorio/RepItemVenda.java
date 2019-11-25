package com.pds1.pi4.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.pds1.pi4.entidades.ItemVenda;

public interface RepItemVenda extends JpaRepository<ItemVenda, Long>{
	
	@Transactional(readOnly = true)
	@Query(value = " SELECT iv.*, pr.nome as produto FROM item_venda iv "
			+ " inner join produto pr on pr.id = iv.produto_id "
			+ " WHERE conferido = 'N' ", nativeQuery = true)
	List<ItemVenda> findItemVendaNaoConferido();
}