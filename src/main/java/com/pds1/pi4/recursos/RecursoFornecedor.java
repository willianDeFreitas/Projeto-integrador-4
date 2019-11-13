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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pds1.pi4.dto.FornecedorDTO;
import com.pds1.pi4.servicos.ServicoFornecedor;

@RestController
@RequestMapping(value = "/fornecedor")
public class RecursoFornecedor {
	
	@Autowired
	private ServicoFornecedor servFornecedor;
	
	
	@GetMapping
	public ResponseEntity<List<FornecedorDTO>> buscar(){
		
		List<FornecedorDTO> list = servFornecedor.buscar();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FornecedorDTO> buscarId(@PathVariable Long id){
		FornecedorDTO obj = servFornecedor.buscarId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET')")
	@PostMapping
	public ResponseEntity<FornecedorDTO> inserir(@RequestBody FornecedorDTO dto){
		FornecedorDTO newDto =  servFornecedor.inserir(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		servFornecedor.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<FornecedorDTO> atualizar(@PathVariable Long id, @RequestBody FornecedorDTO dto){
		dto = servFornecedor.atualizar(id, dto);
		return ResponseEntity.ok().body(dto);
	}
}
