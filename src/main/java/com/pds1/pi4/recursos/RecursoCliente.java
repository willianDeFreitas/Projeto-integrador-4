package com.pds1.pi4.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pds1.pi4.entidades.Cliente;
import com.pds1.pi4.servicos.ServicoCliente;

@RestController
@RequestMapping(value = "/clientes")
public class RecursoCliente {

	@Autowired
	private ServicoCliente servCliente;

	@GetMapping
	public ResponseEntity<List<Cliente>> buscar() {

		List<Cliente> list = servCliente.buscar();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> buscarId(@PathVariable Long id) {
		Cliente obj = servCliente.buscarId(id);
		return ResponseEntity.ok().body(obj);
	}
}
