package com.pds1.pi4.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pds1.pi4.dto.AutenticacaoDTO;
import com.pds1.pi4.dto.TokenDTO;
import com.pds1.pi4.security.JWTUtil;
import com.pds1.pi4.servicos.exceptions.JWTAuthenticationException;

@Service
public class ServicoAutenticacao {
	
		
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTUtil jwtUtil; 
	
	@Transactional(readOnly = true)
	public TokenDTO authenticate(AutenticacaoDTO dto) {
		try {
		var authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
		authenticationManager.authenticate(authToken);
		String token = jwtUtil.generateToken(dto.getEmail());
		return new TokenDTO(dto.getEmail(), token);
		} catch(AuthenticationException e) {
			throw new JWTAuthenticationException("Não autenticado");
		}
	}
	
}
