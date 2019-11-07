package com.pds1.pi4.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pds1.pi4.entidades.ItemVenda;
import com.pds1.pi4.servicos.ServicoItemVenda;

@RestController
@RequestMapping(value = "/itensvenda")
public class RecursoItemVenda {
	
	@Autowired
	private ServicoItemVenda servItemVenda;
	
	@GetMapping
	public ResponseEntity<List<ItemVenda>> buscar(){
		List<ItemVenda> list= servItemVenda.buscar();	
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ItemVenda> buscaId(@PathVariable Long id){
		ItemVenda dto = servItemVenda.buscaPorId(id);
		return ResponseEntity.ok().body(dto) ;
	}
	
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	@PostMapping
//	public ResponseEntity<ItemVendaDTO> inserir(@RequestBody ItemVendaDTO dto){
//		ItemVendaDTO newdto = servItemVenda.inserir(dto);
//		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(newdto.getId()).toUri();
//		return ResponseEntity.created(uri).body(newdto) ;
//	}
//	
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<Void> deletar(@PathVariable Long id){
//		servItemVenda.deletar(id);
//		return ResponseEntity.noContent().build();
//	}
//	
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<ItemVendaDTO> atualizar(@PathVariable Long id, @RequestBody ItemVendaDTO dto){
//		dto = servItemVenda.atualizar(id, dto);
//		return ResponseEntity.ok().body(dto);
//	}
}
