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

import com.pds1.pi4.dto.CategoriaDTO;
import com.pds1.pi4.servicos.ServicoCategoria;

@RestController
@RequestMapping(value = "/categorias")
public class RecursoCategoria {
	
	@Autowired
	private ServicoCategoria servCategoria;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> buscar(){
		List<CategoriaDTO> list= servCategoria.buscar();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> buscaId(@PathVariable Long id){
	
		CategoriaDTO dto = servCategoria.buscaId(id);
		
		return ResponseEntity.ok().body(dto) ;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<CategoriaDTO> inserir(@RequestBody CategoriaDTO dto){
		CategoriaDTO newdto = servCategoria.inserir(dto);
		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newdto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto) ;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		servCategoria.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaDTO dto){
		dto = servCategoria.atualizar(id, dto);
		return ResponseEntity.ok().body(dto);
	}
}
