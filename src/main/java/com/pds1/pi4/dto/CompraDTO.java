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
	private Long usuarioId;
	private String usuarioNome;
	private Long fornecedorId;
	private String fornecedorNome;
	private String fornecedorCnpj;
	private String fornecedorEmail;
	
	public CompraDTO() {
		
	}

	public CompraDTO(Long id, Instant dataReg, CompraStatus compraStatus, Long usuarioId, String usuarioNome, Long fornecedorId,
			String fornecedorNome, String fornecedorCnpj, String fornecedorEmail) {
		super();
		this.id= id;
		this.dataReg = dataReg;
		this.compraStatus = compraStatus;
		this.usuarioId = usuarioId;
		this.usuarioNome = usuarioNome;
		this.fornecedorId = fornecedorId;
		this.fornecedorNome = fornecedorNome;
		this.fornecedorCnpj = fornecedorCnpj;
		this.fornecedorEmail = fornecedorEmail;
	}
	
	public CompraDTO(Compra objComp) {
		if(objComp.getFornecedor() == null) {
			throw new IllegalArgumentException("Error instiating compraDTO: compra was null");
		}
		this.id= objComp.getId();
		this.dataReg = objComp.getDataReg();
		this.compraStatus =objComp.getCompraStatus();
		this.usuarioId = objComp.getUsuario().getId();
		this.usuarioNome = objComp.getUsuario().getNome();
		this.fornecedorId = objComp.getFornecedor().getId();
		this.fornecedorNome = objComp.getFornecedor().getNome();
		this.fornecedorCnpj = objComp.getFornecedor().getCnpj();
		this.fornecedorEmail = objComp.getFornecedor().getEmail();
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

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		usuarioNome = usuarioNome;
	}

	public Long getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(Long fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public String getFornecedorNome() {
		return fornecedorNome;
	}

	public void setFornecedorNome(String fornecedorNome) {
		this.fornecedorNome = fornecedorNome;
	}

	public String getFornecedorCnpj() {
		return fornecedorCnpj;
	}

	public void setFornecedorCnpj(String fornecedorCnpj) {
		this.fornecedorCnpj = fornecedorCnpj;
	}

	public String getFornecedorEmail() {
		return fornecedorEmail;
	}

	public void setFornecedorEmail(String fornecedorEmail) {
		this.fornecedorEmail = fornecedorEmail;
	}
	
	public Compra toEntity() {
		Fornecedor fornecedor =new Fornecedor(fornecedorId, fornecedorNome, fornecedorCnpj, null, null, null);
		Usuario usuario = new Usuario(usuarioId, usuarioNome, null, null);
		return new Compra(id, dataReg, compraStatus, usuario, fornecedor);
	}
}
