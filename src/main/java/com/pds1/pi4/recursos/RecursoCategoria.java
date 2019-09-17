package com.pds1.pi4.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pds1.pi4.entidades.Categoria;
import com.pds1.pi4.servicos.ServicoCategoria;

@RestController
@RequestMapping(value = "/categorias")
public class RecursoCategoria {
	
	@Autowired
	private ServicoCategoria servCategoria;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> buscar(){
		
		List<Categoria> list = servCategoria.buscar();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> buscarId(@PathVariable Long id){
		Categoria obj = servCategoria.buscarId(id);
		return ResponseEntity.ok().body(obj);
	}
}
