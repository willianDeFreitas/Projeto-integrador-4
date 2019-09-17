package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds1.pi4.entidades.Compra;
import com.pds1.pi4.repositorio.RepCompra;

@Service
public class ServicoCompra {
	
	@Autowired
	private RepCompra repCompra;
	
	public List<Compra> buscar(){
		return repCompra.findAll();
	}
	
	public Compra buscarId(Long id) {
		Optional<Compra> obj = repCompra.findById(id);
		return obj.get();
	}
}
