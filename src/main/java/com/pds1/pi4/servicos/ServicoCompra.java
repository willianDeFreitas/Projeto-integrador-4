package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pds1.pi4.dto.CompraDTO;
import com.pds1.pi4.dto.ItemCompraDTO;
import com.pds1.pi4.entidades.Compra;
import com.pds1.pi4.entidades.ItemCompra;
import com.pds1.pi4.entidades.Produto;
import com.pds1.pi4.repositorio.RepCompra;
import com.pds1.pi4.repositorio.RepItemCompra;
import com.pds1.pi4.repositorio.RepProduto;
import com.pds1.pi4.servicos.exceptions.DatabaseException;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoCompra {
	
	@Autowired
	private RepCompra repCompra;
	
	@Autowired
	private RepProduto repProduto;
	
	@Autowired
	private RepItemCompra repItemCompra;
	
	public List<CompraDTO> buscar(){
		List<Compra> list = repCompra.findAll();
		return list.stream().map(e -> new CompraDTO(e)).collect(Collectors.toList());
	}
	
	public CompraDTO buscarId(Long id) {
		Optional<Compra> obj = repCompra.findById(id);
		Compra objComp= obj.orElseThrow(()-> new ResourceNotFoundException(id));
		return new CompraDTO(objComp);
	}

	@Transactional(readOnly = true)
	public List<ItemCompraDTO> buscarItemsComp(Long id) {
		Compra compra = repCompra.getOne(id);
		List<ItemCompra> list = compra.getIntensCompra();
		return list.stream().map(e -> new ItemCompraDTO(e)).collect(Collectors.toList());
	}

	@Transactional
	public CompraDTO inserir(@Valid CompraDTO dto) {
		Compra obj = dto.toEntity();
		obj = repCompra.save(obj);
		
		for (ItemCompraDTO itemDTO : dto.getItensCompra()) {
			Produto produto = repProduto.getOne(itemDTO.getProdutoId());
			ItemCompra itemCompra = new ItemCompra(null, obj, produto, itemDTO.getQtdItemC(), itemDTO.getValorItemC(), itemDTO.getConferido());
			obj.getItensCompra().add(itemCompra);
			
		}
		repItemCompra.saveAll(obj.getIntensCompra());
		
		return new CompraDTO(obj);
		
	}
	
	public void deletar(Long id) {
		try {
			repCompra.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
				throw new ResourceNotFoundException(id);
		}catch (DataIntegrityViolationException e){
			throw new DatabaseException(e.getMessage());
		}
	}
}
