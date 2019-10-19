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
		objPro.setNome(dto.getNome());
		objPro.setPreco(dto.getPreco());
		objPro.setDatareg(dto.getDatareg());
		objPro.setQtd(dto.getQtd());
		objPro.setPreco(dto.getPreco());
		objPro.setVol(dto.getVol());
		Categoria cat = repCategoria.getOne(dto.getCategoriaId());
		objPro.setCategoria(cat);
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
