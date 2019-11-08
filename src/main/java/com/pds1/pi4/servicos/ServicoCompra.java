package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pds1.pi4.dto.CompraDTO;
import com.pds1.pi4.dto.ItemCompraDTO;
import com.pds1.pi4.dto.VendaDTO;
import com.pds1.pi4.entidades.Compra;
import com.pds1.pi4.entidades.ItemCompra;
import com.pds1.pi4.entidades.Venda;
import com.pds1.pi4.repositorio.RepCompra;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoCompra {
	
	@Autowired
	private RepCompra repCompra;
	
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
		Set<ItemCompra> set = compra.getIntemsCompra();
		return set.stream().map(e -> new ItemCompraDTO(e)).collect(Collectors.toList());
	}

	public CompraDTO inserir(@Valid CompraDTO dto) {
		Compra objUs = dto.toEntity();
		objUs = repCompra.save(objUs);
		return new CompraDTO(objUs);
		
	}
}
