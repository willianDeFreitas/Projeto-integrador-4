package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pds1.pi4.dto.ProdutoDTO;
import com.pds1.pi4.entidades.Categoria;
import com.pds1.pi4.entidades.Produto;
import com.pds1.pi4.repositorio.RepCategoria;
import com.pds1.pi4.repositorio.RepProduto;
import com.pds1.pi4.servicos.exceptions.DatabaseException;
import com.pds1.pi4.servicos.exceptions.ResourceNotFoundException;

@Service
public class ServicoProduto {
	
	@Autowired
	private RepProduto repProduto;
	
	@Autowired
	private RepCategoria repCategoria;
	
	public List<ProdutoDTO> buscar(){
		List<Produto> list = repProduto.findAll();
		return list.stream().map(e -> new ProdutoDTO(e)).collect(Collectors.toList());
	}
	
	public ProdutoDTO buscarId(Long id) {
		Optional<Produto> obj = repProduto.findById(id);
		Produto objPro = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new ProdutoDTO(objPro);
	}
	
	@Transactional 
	public ProdutoDTO inserir(ProdutoDTO dto) {
		Produto objPro =  dto.toEntity();
		objPro = repProduto.save(objPro);
		return new ProdutoDTO(objPro);
	}

		
	@Transactional
	public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
		try {
			Produto objPro = repProduto.getOne(id);
			atualizarData(objPro, dto);
			objPro = repProduto.save(objPro);
		return new ProdutoDTO(objPro);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void atualizarData(Produto objPro, ProdutoDTO dto) {
		if (dto.getNome() != null) {
			objPro.setNome(dto.getNome());			
		}
		
		if (dto.getPreco() > 0.0) {
			objPro.setPreco(dto.getPreco());			
		}
		
		if (dto.getDatareg() != null) {
			objPro.setDatareg(dto.getDatareg());			
		}
		
		if (dto.getVol() != null) {
			objPro.setVol(dto.getVol());			
		}
		
		if (dto.getCategoriaId() > 0) {
			Categoria cat = repCategoria.getOne(dto.getCategoriaId());
			objPro.setCategoria(cat);			
		}
		
		objPro.setQtd(dto.getQtd());
	}

	public void excluir(Long id) {
		try {
		 repProduto.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch (DataIntegrityViolationException e){
			throw new DatabaseException(e.getMessage());
			}
	}

	
}
