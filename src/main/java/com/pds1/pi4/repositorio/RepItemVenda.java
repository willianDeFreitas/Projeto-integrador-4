package com.pds1.pi4.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.pds1.pi4.entidades.ItemVenda;

public interface RepItemVenda extends JpaRepository<ItemVenda, Long>{
	
	@Transactional(readOnly = true)
	@Query(value = " SELECT it.*, pr.nome as produto, cl.nome as cliente FROM item_venda it "
			+ " inner join produto pr on pr.id = it.produto_id "
			+ " inner join venda ve on ve.id = it.venda_id "
			+ " inner join cliente cl on cl.id = ve.cliente_id "
			+ " WHERE conferido = 'N' ", nativeQuery = true)
	List<ItemVenda> findItemVendaNaoConferido();
}