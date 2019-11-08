package com.pds1.pi4.recursos;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pds1.pi4.dto.CompraDTO;
import com.pds1.pi4.dto.ItemCompraDTO;
import com.pds1.pi4.dto.VendaDTO;
import com.pds1.pi4.servicos.ServicoCompra;

@RestController
@RequestMapping(value = "/compras")
public class RecursoCompra {
	
	@Autowired
	private ServicoCompra servCompra;
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET')")
	@GetMapping
	public ResponseEntity<List<CompraDTO>> buscar(){
		
		List<CompraDTO> list = servCompra.buscar();
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<CompraDTO> buscarId(@PathVariable Long id){
		CompraDTO dto = servCompra.buscarId(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{id}/items")
	public ResponseEntity<List<ItemCompraDTO>> buscarItemsComp(@PathVariable Long id){
		List<ItemCompraDTO> list = servCompra.buscarItemsComp(id);
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET')")
	@PostMapping
	public ResponseEntity<CompraDTO> inserir(@Valid @RequestBody CompraDTO dto){
		CompraDTO newDto =  servCompra.inserir(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
}
