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

import com.pds1.pi4.dto.ClienteDTO;
import com.pds1.pi4.entidades.Cliente;
import com.pds1.pi4.repositorio.RepCliente;
import com.pds1.pi4.servicos.exceptions.DatabaseException;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoCliente {
	
	@Autowired
	private RepCliente repCliente;
	
	public List<ClienteDTO> buscar(){
		List<Cliente> list =  repCliente.findAll();
		return list.stream().map(e -> new ClienteDTO(e)).collect(Collectors.toList());
	}
	
	public ClienteDTO buscarId(Long id) {
		Optional<Cliente> obj = repCliente.findById(id);
		Cliente objCli = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new ClienteDTO(objCli);
	}
	
	public ClienteDTO inserir(ClienteDTO dto) {
		Cliente objCli = dto.toEntity();
		objCli = repCliente.save(objCli);
		return new ClienteDTO(objCli);
	}
	
	public void excluir(Long id) {
		try {
			repCliente.deleteById(id);
			} catch(EmptyResultDataAccessException e) {
				throw new ResourceNotFoundException(id);
			}catch(DataIntegrityViolationException e) {
				throw new DatabaseException(e.getMessage());
			}
	}
	
	@Transactional
	public ClienteDTO atualizar(Long id, ClienteDTO dto) {
		try {
		Cliente  objCli = repCliente.getOne(id);
		atualizarData(objCli, dto);
		objCli= repCliente.save(objCli);
		return new ClienteDTO(objCli);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void atualizarData(Cliente objCli, ClienteDTO dto) {
		objCli.setNome(dto.getNome());
		objCli.setCpf(dto.getCpf());
		objCli.setEnd(dto.getEnd());
		objCli.setEmail(dto.getEmail());
		objCli.setTel(dto.getTel());
	}
}
