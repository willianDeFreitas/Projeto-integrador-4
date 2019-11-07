package com.pds1.pi4.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds1.pi4.entidades.ItemVenda;
import com.pds1.pi4.repositorio.RepItemVenda;

@Service
public class ServicoItemVenda {
	
	@Autowired
	private RepItemVenda repItemvenda;
	
	public List<ItemVenda> buscar() {
		return repItemvenda.findAll();
//		List<ItemVenda> list= repItemvenda.findAll();
//		return list.stream().map(e -> new ItemvendaDTO(e)).collect(Collectors.toList());
	}

	public ItemVenda buscaPorId(Long id) {
		Optional<ItemVenda> obj = repItemvenda.findById(id);
		//Itemvenda objCat= obj.orElseThrow(()-> new ResourceNotFoundException(id));
		//return new ItemvendaDTO(objCat);
		return obj.get();
	}
	
//	public ItemvendaDTO inserir(ItemvendaDTO dto) {
//		Itemvenda objCat = dto.toEntity();
//		objCat = repItemvenda.save(objCat);
//		return new ItemvendaDTO(objCat);
//	}
//	
//	public void deletar(Long id) {
//		try {
//			repItemvenda.deleteById(id);
//			}catch (EmptyResultDataAccessException e) {
//				throw new ResourceNotFoundException(id);
//			}catch (DataIntegrityViolationException e){
//				throw new DatabaseException(e.getMessage());
//				}
//	}
//	
//	@Transactional
//	public ItemvendaDTO atualizar(Long id, ItemvendaDTO dto) {
//		try {
//			Itemvenda objCat = repItemvenda.getOne(id);
//			atualizarData(objCat, dto);
//			objCat = repItemvenda.save(objCat);
//			return new ItemvendaDTO(objCat);
//			} catch (EntityNotFoundException e) {
//				throw new ResourceNotFoundException(id);
//			}
//	}
//
//	private void atualizarData(Itemvenda objCat, ItemvendaDTO dto) {
//		objCat.setNome(dto.getNome());
//		
//	}
}
