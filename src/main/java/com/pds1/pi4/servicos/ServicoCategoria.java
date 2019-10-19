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

import com.pds1.pi4.dto.CategoriaDTO;
import com.pds1.pi4.entidades.Categoria;
import com.pds1.pi4.repositorio.RepCategoria;
import com.pds1.pi4.servicos.exceptions.DatabaseException;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoCategoria {
	
	@Autowired
	private RepCategoria repCategoria;
	
	public List<CategoriaDTO> buscar() {

		List<Categoria> list= repCategoria.findAll();
		return list.stream().map(e -> new CategoriaDTO(e)).collect(Collectors.toList());
	}

	public CategoriaDTO buscaId(Long id) {
		Optional<Categoria> obj = repCategoria.findById(id);
		Categoria objCat= obj.orElseThrow(()-> new ResourceNotFoundException(id));
		return new CategoriaDTO(objCat);
	}
	
	public CategoriaDTO inserir(CategoriaDTO dto) {
		Categoria objCat = dto.toEntity();
		objCat = repCategoria.save(objCat);
		return new CategoriaDTO(objCat);
	}
	
	public void deletar(Long id) {
		try {
			repCategoria.deleteById(id);
			}catch (EmptyResultDataAccessException e) {
				throw new ResourceNotFoundException(id);
			}catch (DataIntegrityViolationException e){
				throw new DatabaseException(e.getMessage());
				}
	}
	
	@Transactional
	public CategoriaDTO atualizar(Long id, CategoriaDTO dto) {
		try {
			Categoria objCat = repCategoria.getOne(id);
			atualizarData(objCat, dto);
			objCat = repCategoria.save(objCat);
			return new CategoriaDTO(objCat);
			} catch (EntityNotFoundException e) {
				throw new ResourceNotFoundException(id);
			}
	}

	private void atualizarData(Categoria objCat, CategoriaDTO dto) {
		objCat.setNome(dto.getNome());
		
	}
}
