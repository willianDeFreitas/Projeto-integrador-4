package com.pds1.pi4.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pds1.pi4.entidades.Fornecedor;
import com.pds1.pi4.servicos.ServicoFornecedor;

@RestController
@RequestMapping(value = "/fornecedor")
public class RecursoFornecedor {
	
	@Autowired
	private ServicoFornecedor servFornecedor;
	
	@GetMapping
	public ResponseEntity<List<Fornecedor>> buscar(){
		
		List<Fornecedor> list = servFornecedor.buscar();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Fornecedor> buscarId(@PathVariable Long id){
		Fornecedor obj = servFornecedor.buscarId(id);
		return ResponseEntity.ok().body(obj);
	}
}
