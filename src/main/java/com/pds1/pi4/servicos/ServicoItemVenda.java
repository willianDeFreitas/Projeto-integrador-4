package com.pds1.pi4.servicos;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pds1.pi4.dto.ItemVendaDTO;
import com.pds1.pi4.entidades.ItemVenda;
import com.pds1.pi4.repositorio.RepItemVenda;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoItemVenda {
	
	@Autowired
	private RepItemVenda repItemVenda;
	
	public List<ItemVendaDTO> buscarItemVendaNaoConferido(){
		List<ItemVenda> list = repItemVenda.findItemVendaNaoConferido();
		return list.stream().map(e -> new ItemVendaDTO(e)).collect(Collectors.toList());
	}
	
	@Transactional
	public ItemVendaDTO atualizar(Long id, ItemVendaDTO dto) {
		try {
		ItemVenda  objUs = repItemVenda.getOne(id);
		atualizarData(objUs, dto);
		objUs= repItemVenda.save(objUs);
		return new ItemVendaDTO(objUs);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void atualizarData(ItemVenda objUs, ItemVendaDTO dto) {
		objUs.setConferido(dto.getConferido());
	}
}