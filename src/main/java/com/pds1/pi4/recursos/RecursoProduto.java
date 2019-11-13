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

import com.pds1.pi4.dto.ProdutoDTO;
import com.pds1.pi4.servicos.ServicoProduto;

@RestController
@RequestMapping(value = "/produtos")
public class RecursoProduto {
	
	@Autowired
	private ServicoProduto servProduto;
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET', 'ESTOQ')")
	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> buscar(){
		
		List<ProdutoDTO> list = servProduto.buscar();
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET', 'ESTOQ')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> buscarId(@PathVariable Long id){
		ProdutoDTO dto = servProduto.buscarId(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<ProdutoDTO> inserir(@RequestBody ProdutoDTO dto){
		ProdutoDTO newDto =  servProduto.inserir(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		servProduto.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoDTO dto){
		dto = servProduto.atualizar(id, dto);
		return ResponseEntity.ok().body(dto);
	}
}
