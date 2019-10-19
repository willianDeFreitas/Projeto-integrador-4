package com.pds1.pi4.dto;

import java.io.Serializable;

import com.pds1.pi4.entidades.Fornecedor;

public class FornecedorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String cnpj;
	private String end;
	private String email;
	private String telefone;
	
	public FornecedorDTO() {
		
	}

	
	
	public FornecedorDTO(Long id, String nome, String cnpj, String end, String email, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.end = end;
		this.email = email;
		this.telefone = telefone;
	}



	public FornecedorDTO(Fornecedor objFor) {
		
		this.id = objFor.getId();
		this.nome = objFor.getNome();
		this.cnpj = objFor.getCnpj();
		this.end = objFor.getEnd();
		this.email = objFor.getEmail();
		this.telefone = objFor.getTelefone();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getCnpj() {
		return cnpj;
	}



	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}



	public String getEnd() {
		return end;
	}



	public void setEnd(String end) {
		this.end = end;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
		
	public Fornecedor toEntity() {
		return new Fornecedor(id, nome, cnpj, end, email, telefone);
	}
}
