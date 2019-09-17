package com.pds1.pi4.recursos;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pds1.pi4.dto.InserirUsuarioDTO;
import com.pds1.pi4.dto.UsuarioDTO;
import com.pds1.pi4.entidades.Usuario;
import com.pds1.pi4.servicos.ServicoUsuario;

@RestController
@RequestMapping(value = "/usuarios")
public class RecursoUsuario {
	
	@Autowired
	private ServicoUsuario servUsuario;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> buscar(){
		
		List<UsuarioDTO> list = servUsuario.buscar();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> buscarId(@PathVariable Long id){
		UsuarioDTO dto = servUsuario.buscarId(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> inserir(@RequestBody InserirUsuarioDTO dto){
		UsuarioDTO newDto =  servUsuario.inserir(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		servUsuario.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto){
		dto = servUsuario.atualizar(id, dto);
		return ResponseEntity.ok().body(dto);
	}
}
