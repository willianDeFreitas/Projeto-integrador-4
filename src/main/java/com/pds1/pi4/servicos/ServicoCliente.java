package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds1.pi4.entidades.Cliente;
import com.pds1.pi4.repositorio.RepCliente;

@Service
public class ServicoCliente {
	
	@Autowired
	private RepCliente repCliente;
	
	public List<Cliente> buscar(){
		return repCliente.findAll();
	}
	
	public Cliente buscarId(Long id) {
		Optional<Cliente> obj = repCliente.findById(id);
		return obj.get();
	}
}
