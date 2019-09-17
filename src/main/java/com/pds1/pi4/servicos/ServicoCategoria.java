package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds1.pi4.entidades.Categoria;
import com.pds1.pi4.repositorio.RepCategoria;

@Service
public class ServicoCategoria {
	
	@Autowired
	private RepCategoria repCategoria;
	
	public List<Categoria> buscar(){
		return repCategoria.findAll();
	}
	
	public Categoria buscarId(Long id) {
		Optional<Categoria> obj = repCategoria.findById(id);
		return obj.get();
	}
}
