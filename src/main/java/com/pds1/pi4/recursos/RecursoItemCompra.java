package com.pds1.pi4.recursos;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pds1.pi4.dto.ItemCompraDTO;
import com.pds1.pi4.servicos.ServicoItemCompra;

@RestController
@RequestMapping(value = "/itenscompra")
public class RecursoItemCompra {
	
	@Autowired
	private ServicoItemCompra servItemCompra;
	
	@PreAuthorize("hasAnyRole('ADMIN','ESTOQ')")
	@GetMapping(value = "/naoconf")
	public ResponseEntity<List<ItemCompraDTO>> buscar(){
		List<ItemCompraDTO> list = servItemCompra.buscarItemCompraNaoConferido();
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','ESTOQ')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ItemCompraDTO> atualizar(@PathVariable Long id,@Valid @RequestBody ItemCompraDTO dto){
		dto = servItemCompra.atualizar(id, dto);
		return ResponseEntity.ok().body(dto);
	}
}