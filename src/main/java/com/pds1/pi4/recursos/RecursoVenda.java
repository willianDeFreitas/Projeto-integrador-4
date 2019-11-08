package com.pds1.pi4.recursos;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pds1.pi4.dto.ItemVendaDTO;
import com.pds1.pi4.dto.VendaDTO;
import com.pds1.pi4.servicos.ServicoVenda;

@RestController
@RequestMapping(value = "/vendas")
public class RecursoVenda {
	
	@Autowired
	private ServicoVenda servVenda;
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET')")
	@GetMapping
	public ResponseEntity<List<VendaDTO>> buscar(){
		List<VendaDTO> list = servVenda.buscar();
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<VendaDTO> buscarId(@PathVariable Long id){
		VendaDTO dto = servVenda.buscarId(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET')")
	@PostMapping
	public ResponseEntity<VendaDTO> inserir(@RequestBody ItemVendaDTO dto){
		VendaDTO newdto = servVenda.inserir(dto);
		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newdto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		servVenda.deletar(id);
		return ResponseEntity.noContent().build();
	}
}