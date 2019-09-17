package com.pds1.pi4.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pds1.pi4.entidades.Compra;
import com.pds1.pi4.servicos.ServicoCompra;

@RestController
@RequestMapping(value = "/compras")
public class RecursoCompra {
	
	@Autowired
	private ServicoCompra servCompra;
	
	@GetMapping
	public ResponseEntity<List<Compra>> buscar(){
		
		List<Compra> list = servCompra.buscar();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Compra> buscarId(@PathVariable Long id){
		Compra obj = servCompra.buscarId(id);
		return ResponseEntity.ok().body(obj);
	}
}
