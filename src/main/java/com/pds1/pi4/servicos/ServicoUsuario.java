package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pds1.pi4.dto.InserirUsuarioDTO;
import com.pds1.pi4.dto.UsuarioDTO;
import com.pds1.pi4.entidades.Usuario;
import com.pds1.pi4.repositorio.RepUsuario;
import com.pds1.pi4.servicos.exceptions.DatabaseException;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoUsuario {
	
	@Autowired
	private RepUsuario repUsuario;
	
	public List<UsuarioDTO> buscar(){
		List<Usuario> list= repUsuario.findAll();
		return list.stream().map(e -> new UsuarioDTO(e)).collect(Collectors.toList());
	}
	
	public UsuarioDTO buscarId(Long id) {
		Optional<Usuario> obj = repUsuario.findById(id);
		Usuario objUs=  obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new UsuarioDTO(objUs);
	}
	
	public UsuarioDTO inserir(InserirUsuarioDTO dto) {
		Usuario objUs = dto.toEntity();
		objUs = repUsuario.save(objUs);
		return new UsuarioDTO(objUs);
	}
	
	public void excluir(Long id) {
		try {
		repUsuario.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	@Transactional
	public UsuarioDTO atualizar(Long id, UsuarioDTO dto) {
		try {
		Usuario  objUs = repUsuario.getOne(id);
		atualizarData(objUs, dto);
		objUs= repUsuario.save(objUs);
		return new UsuarioDTO(objUs);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void atualizarData(Usuario objUs, UsuarioDTO dto) {
		objUs.setNome(dto.getNome());
		objUs.setEmail(dto.getEmail());
		objUs.setSetor(dto.getSetor());		
	}
	
}