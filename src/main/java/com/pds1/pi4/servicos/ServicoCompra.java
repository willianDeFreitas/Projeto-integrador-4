package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds1.pi4.dto.CompraDTO;
import com.pds1.pi4.entidades.Compra;
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
}
