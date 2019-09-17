package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds1.pi4.entidades.Produto;
import com.pds1.pi4.repositorio.RepProduto;

@Service
public class ServicoProduto {
	
	@Autowired
	private RepProduto repProduto;
	
	public List<Produto> buscar(){
		return repProduto.findAll();
	}
	
	public Produto buscarId(Long id) {
		Optional<Produto> obj = repProduto.findById(id);
		return obj.get();
	}
}
