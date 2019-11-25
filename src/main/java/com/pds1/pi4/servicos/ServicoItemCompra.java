package com.pds1.pi4.servicos;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pds1.pi4.dto.ItemCompraDTO;
import com.pds1.pi4.entidades.ItemCompra;
import com.pds1.pi4.repositorio.RepItemCompra;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoItemCompra {
	
	@Autowired
	private RepItemCompra repItemCompra;
	
	public List<ItemCompraDTO> buscarItemCompraNaoConferido(){
		List<ItemCompra> list = repItemCompra.findItemCompraNaoConferido();
		return list.stream().map(e -> new ItemCompraDTO(e)).collect(Collectors.toList());
	}
	
	@Transactional
	public ItemCompraDTO atualizar(Long id, ItemCompraDTO dto) {
		try {
		ItemCompra objUs = repItemCompra.getOne(id);
		atualizarDados(objUs, dto);
		objUs= repItemCompra.save(objUs);
		return new ItemCompraDTO(objUs);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void atualizarDados(ItemCompra objUs, ItemCompraDTO dto) {
		objUs.setConferido(dto.getConferido());
	}
}