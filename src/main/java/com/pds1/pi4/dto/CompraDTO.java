package com.pds1.pi4.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pds1.pi4.entidades.Compra;
import com.pds1.pi4.entidades.Fornecedor;
import com.pds1.pi4.entidades.Usuario;
import com.pds1.pi4.entidades.enums.CompraStatus;

public class CompraDTO {

	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dataReg;
	private CompraStatus compraStatus;
	private String fornecedorNome;
	
	
	public CompraDTO() {
		
	}

	public CompraDTO(Long id, Instant dataReg, CompraStatus compraStatus, String fornecedorNome) {
		super();
		this.id= id;
		this.dataReg = dataReg;
		this.compraStatus = compraStatus;
		this.fornecedorNome = fornecedorNome;
		
	}
	
	public CompraDTO(Compra objComp) {
		if(objComp.getFornecedor() == null) {
			throw new IllegalArgumentException("Error instiating compraDTO: compra was null");
		}
		this.id= objComp.getId();
		this.dataReg = objComp.getDataReg();
		this.compraStatus =objComp.getCompraStatus();
		this.fornecedorNome = objComp.getFornecedor().getNome();
		
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDataReg() {
		return dataReg;
	}

	public void setDataReg(Instant dataReg) {
		this.dataReg = dataReg;
	}

	public CompraStatus getCompraStatus() {
		return compraStatus;
	}

	public void setCompraStatus(CompraStatus compraStatus) {
		this.compraStatus = compraStatus;
	}

	public String getFornecedorNome() {
		return fornecedorNome;
	}

	public void setFornecedorNome(String fornecedorNome) {
		this.fornecedorNome = fornecedorNome;
	}

	
	public Compra toEntity() {
		Fornecedor fornecedor =new Fornecedor(null,fornecedorNome, null, null, null, null);
		return new Compra(id, dataReg, compraStatus, fornecedor);
	}
}
