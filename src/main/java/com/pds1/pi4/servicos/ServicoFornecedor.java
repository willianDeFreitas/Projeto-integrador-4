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

import com.pds1.pi4.dto.FornecedorDTO;
import com.pds1.pi4.entidades.Fornecedor;
import com.pds1.pi4.repositorio.RepFornecedor;
import com.pds1.pi4.servicos.exceptions.DatabaseException;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoFornecedor {

	@Autowired
	private RepFornecedor repFornecedor;

	public List<FornecedorDTO> buscar() {
		List<Fornecedor> list = repFornecedor.findAll();
		return list.stream().map(e -> new FornecedorDTO(e)).collect(Collectors.toList());
	}

	public FornecedorDTO buscarId(Long id) {
		Optional<Fornecedor> obj = repFornecedor.findById(id);
		Fornecedor objFor = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new FornecedorDTO(objFor);
	}

	public FornecedorDTO inserir(FornecedorDTO dto) {
		Fornecedor objFor = dto.toEntity();
		objFor = repFornecedor.save(objFor);
		return new FornecedorDTO(objFor);
	}

	public void excluir(Long id) {
		try {
			repFornecedor.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public FornecedorDTO atualizar(Long id, FornecedorDTO dto) {
		try {
			Fornecedor objFor = repFornecedor.getOne(id);
			atualizarData(objFor, dto);
			objFor = repFornecedor.save(objFor);
			return new FornecedorDTO(objFor);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void atualizarData(Fornecedor objFor, FornecedorDTO dto) {
		objFor.setNome(dto.getNome());
		objFor.setCnpj(dto.getCnpj());
		objFor.setEnd(dto.getEnd());
		objFor.setEmail(dto.getEmail());
		objFor.setTelefone(dto.getTelefone());
	}
}
