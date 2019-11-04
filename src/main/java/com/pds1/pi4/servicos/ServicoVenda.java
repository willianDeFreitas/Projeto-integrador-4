package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds1.pi4.dto.InserirUsuarioDTO;
import com.pds1.pi4.dto.UsuarioDTO;
import com.pds1.pi4.dto.VendaDTO;
import com.pds1.pi4.entidades.Usuario;
import com.pds1.pi4.entidades.Venda;
import com.pds1.pi4.repositorio.RepVenda;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoVenda {
	
	@Autowired
	private RepVenda repVenda;
	
	public List<VendaDTO> buscar(){
		List<Venda> list = repVenda.findAll();
		return list.stream().map(e -> new VendaDTO(e)).collect(Collectors.toList());
	}
	
	public VendaDTO buscarId(Long id) {
		Optional<Venda> obj = repVenda.findById(id);
		Venda objVend = obj.orElseThrow(()-> new ResourceNotFoundException(id));
		return new VendaDTO(objVend);
	}
	
	public VendaDTO inserir(VendaDTO dto) {
		Venda objUs = dto.toEntity();
		objUs = repVenda.save(objUs);
		return new VendaDTO(objUs);
	}
}
