package com.pds1.pi4.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pds1.pi4.entidades.Produto;
import com.pds1.pi4.servicos.ServicoProduto;

@RestController
@RequestMapping(value = "/produtos")
public class RecursoProduto {
	
	@Autowired
	private ServicoProduto servProduto;
	
	@GetMapping
	public ResponseEntity<List<Produto>> buscar(){
		
		List<Produto> list = servProduto.buscar();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> buscarId(@PathVariable Long id){
		Produto obj = servProduto.buscarId(id);
		return ResponseEntity.ok().body(obj);
	}
}
