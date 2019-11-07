package com.pds1.pi4.servicos;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds1.pi4.dto.ItemVendaDTO;
import com.pds1.pi4.dto.VendaDTO;
import com.pds1.pi4.dto.VendaItemVendaDTO;
import com.pds1.pi4.entidades.ItemVenda;
import com.pds1.pi4.entidades.ItemVendaOLD;
import com.pds1.pi4.entidades.Venda;
import com.pds1.pi4.repositorio.RepItemVenda;
import com.pds1.pi4.repositorio.RepVenda;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoVenda {
	
	@Autowired
	private RepVenda repVenda;
	
	@Autowired
	private RepItemVenda itemVendaRepositorio;
	
	public List<Venda> buscar(){
		return repVenda.findAll();
		//List<Venda> list = repVenda.findAll();
		//return list.stream().map(e -> new VendaDTO(e)).collect(Collectors.toList());
	}
	
	public Venda buscarPorId(Long id) {
		Optional<Venda> obj = repVenda.findById(id);
		//Venda objVend = obj.orElseThrow(()-> new ResourceNotFoundException(id));
		//return new VendaDTO(objVend);
		return obj.get();
	}
	
//	public VendaDTO inserir(VendaItemVendaDTO dto) {
//		Venda entity = dto.toEntity();
//		setVendaItemVenda(entity, dto.getItemVenda());
//		entity = repVenda.save(entity);
//		return new VendaDTO(entity);
//	}
//
//	private void setVendaItemVenda(Venda entity, List<ItemVendaDTO> itemVendas) {
//		entity.getItemsVenda().clear();
//		for (ItemVendaDTO dto : itemVendas) {
//			ItemVenda itemVenda = itemVendaRepositorio.getOne((long) dto.getId());
//			entity.getItemsVenda().add(itemVenda);
//		}
//	}
}
