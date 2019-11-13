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

import com.pds1.pi4.dto.ClienteDTO;
import com.pds1.pi4.servicos.ServicoCliente;

@RestController
@RequestMapping(value = "/clientes")
public class RecursoCliente {

	@Autowired
	private ServicoCliente servCliente;

	@PreAuthorize("hasAnyRole('ADMIN','SECRET')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> buscar() {

		List<ClienteDTO> list = servCliente.buscar();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> buscarId(@PathVariable Long id) {
		ClienteDTO obj = servCliente.buscarId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET')")
	@PostMapping
	public ResponseEntity<ClienteDTO> inserir(@RequestBody ClienteDTO dto){
		ClienteDTO newDto =  servCliente.inserir(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		servCliente.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','SECRET')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteDTO dto){
		dto = servCliente.atualizar(id, dto);
		return ResponseEntity.ok().body(dto);
	}
}
