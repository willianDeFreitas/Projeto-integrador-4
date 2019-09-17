package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds1.pi4.entidades.Venda;
import com.pds1.pi4.repositorio.RepVenda;

@Service
public class ServicoVenda {
	
	@Autowired
	private RepVenda repVenda;
	
	public List<Venda> buscar(){
		return repVenda.findAll();
	}
	
	public Venda buscarId(Long id) {
		Optional<Venda> obj = repVenda.findById(id);
		return obj.get();
	}
}
