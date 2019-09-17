package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds1.pi4.entidades.Fornecedor;
import com.pds1.pi4.repositorio.RepFornecedor;

@Service
public class ServicoFornecedor {
	
	@Autowired
	private RepFornecedor repFornecedor;
	
	public List<Fornecedor> buscar(){
		return repFornecedor.findAll();
	}
	
	public Fornecedor buscarId(Long id) {
		Optional<Fornecedor> obj = repFornecedor.findById(id);
		return obj.get();
	}
}
