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

import com.pds1.pi4.dto.ItemVendaDTO;
import com.pds1.pi4.servicos.ServicoItemVenda;

@RestController
@RequestMapping(value = "/itensvenda")
public class RecursoItemVenda {
	
	@Autowired
	private ServicoItemVenda servItemVenda;
	
	@PreAuthorize("hasAnyRole('ADMIN','ESTOQ')")
	@GetMapping(value = "/naoconf")
	public ResponseEntity<List<ItemVendaDTO>> buscar(){
		List<ItemVendaDTO> list = servItemVenda.buscarItemVendaNaoConferido();
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','ESTOQ')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ItemVendaDTO> atualizar(@PathVariable Long id,@Valid @RequestBody ItemVendaDTO dto){
		dto = servItemVenda.atualizar(id, dto);
		return ResponseEntity.ok().body(dto);
	}
}