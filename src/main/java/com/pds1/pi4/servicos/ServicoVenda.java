package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pds1.pi4.dto.ItemCompraDTO;
import com.pds1.pi4.dto.ItemVendaDTO;
import com.pds1.pi4.dto.VendaDTO;
import com.pds1.pi4.entidades.Compra;
import com.pds1.pi4.entidades.ItemCompra;
import com.pds1.pi4.entidades.ItemVenda;
import com.pds1.pi4.entidades.Produto;
import com.pds1.pi4.entidades.Venda;
import com.pds1.pi4.repositorio.RepItemVenda;
import com.pds1.pi4.repositorio.RepProduto;
import com.pds1.pi4.repositorio.RepVenda;
import com.pds1.pi4.servicos.exceptions.DatabaseException;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoVenda {
	
	@Autowired
	private RepVenda repVenda;
	
	@Autowired
	private RepItemVenda repItemVenda;
	
	@Autowired
	private ServicoVenda servItemVenda;
	
	@Autowired
	private RepProduto repProduto;
	
	public List<VendaDTO> buscar(){
		List<Venda> list = repVenda.findAll();
		return list.stream().map(e -> new VendaDTO(e)).collect(Collectors.toList());
	}
	
	public VendaDTO buscarId(Long id) {
		Optional<Venda> obj = repVenda.findById(id);
		Venda objVend = obj.orElseThrow(()-> new ResourceNotFoundException(id));
		return new VendaDTO(objVend);
	}
	
	@Transactional
	public VendaDTO inserir(VendaDTO dto) {

		Venda obj = dto.toEntity();
		obj = repVenda.save(obj);
		
		for (ItemVendaDTO itemDTO : dto.getItensVenda()) {
			Produto produto = repProduto.getOne(itemDTO.getProdutoId());
			ItemVenda itemVenda = new ItemVenda(null, obj, produto, itemDTO.getQtdItemV(), itemDTO.getValorItemV());
			obj.getItemsVenda().add(itemVenda);
			
		}
		repItemVenda.saveAll(obj.getItemsVenda());
				
		return new VendaDTO(obj);
	}
	
	public void deletar(Long id) {
		try {
			repVenda.deleteById(id);
			}catch (EmptyResultDataAccessException e) {
				throw new ResourceNotFoundException(id);
			}catch (DataIntegrityViolationException e){
				throw new DatabaseException(e.getMessage());
				}
	}

	public List<ItemVendaDTO> buscarItensVenda(Long id) {
		Venda venda = repVenda.getOne(id);
		List<ItemVenda> list = venda.getItemsVenda();
		return list.stream().map(e -> new ItemVendaDTO(e)).collect(Collectors.toList());
	}
}