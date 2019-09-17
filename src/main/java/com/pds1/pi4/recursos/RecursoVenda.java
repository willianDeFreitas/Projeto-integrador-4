package com.pds1.pi4.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pds1.pi4.entidades.Venda;
import com.pds1.pi4.servicos.ServicoVenda;

@RestController
@RequestMapping(value = "/vendas")
public class RecursoVenda {
	
	@Autowired
	private ServicoVenda servVenda;
	
	@GetMapping
	public ResponseEntity<List<Venda>> buscar(){
		
		List<Venda> list = servVenda.buscar();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Venda> buscarId(@PathVariable Long id){
		Venda obj = servVenda.buscarId(id);
		return ResponseEntity.ok().body(obj);
	}
}
