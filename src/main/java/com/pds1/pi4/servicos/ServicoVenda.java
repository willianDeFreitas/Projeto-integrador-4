package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pds1.pi4.dto.ItemVendaDTO;
import com.pds1.pi4.dto.VendaDTO;
import com.pds1.pi4.entidades.ItemVenda;
import com.pds1.pi4.entidades.Venda;
import com.pds1.pi4.repositorio.RepItemVenda;
import com.pds1.pi4.repositorio.RepVenda;
import com.pds1.pi4.servicos.exceptions.DatabaseException;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoVenda {
	
	@Autowired
	private RepVenda repVenda;
	
	@Autowired
	private RepItemVenda repItemVenda;
	
	public List<VendaDTO> buscar(){
		List<Venda> list = repVenda.findAll();
		return list.stream().map(e -> new VendaDTO(e)).collect(Collectors.toList());
	}
	
	public VendaDTO buscarId(Long id) {
		Optional<Venda> obj = repVenda.findById(id);
		Venda objVend = obj.orElseThrow(()-> new ResourceNotFoundException(id));
		return new VendaDTO(objVend);
	}
	
	public VendaDTO inserir(ItemVendaDTO dto) {
		Venda obj = dto.toEntity();
		setItensVenda(obj, dto.getItensVenda());
		obj = repVenda.save(obj);
		return new VendaDTO(obj);
	}
	
	private void setItensVenda(Venda obj, List<ItemVenda> itensVenda) {
		obj.getItemsVenda().clear();
		for (ItemVenda dto : itensVenda) {
			ItemVenda itemVenda = repItemVenda.getOne(dto.getIdItemVenda());
			obj.getItemsVenda().add(itemVenda);
		}
		
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
}