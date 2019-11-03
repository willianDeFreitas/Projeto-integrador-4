package com.pds1.pi4.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pds1.pi4.dto.AutenticacaoDTO;
import com.pds1.pi4.dto.TokenDTO;
import com.pds1.pi4.servicos.ServicoAutenticacao;

@RestController
@RequestMapping(value ="/auth")
public class RecursoAutenticacao {
	
	@Autowired
	private ServicoAutenticacao servico;
	
	@PostMapping("/login")
	public ResponseEntity<TokenDTO> login(@RequestBody AutenticacaoDTO dto){
		TokenDTO tokenDTO = servico.authenticate(dto);
		return ResponseEntity.ok().body(tokenDTO);
	}

}
