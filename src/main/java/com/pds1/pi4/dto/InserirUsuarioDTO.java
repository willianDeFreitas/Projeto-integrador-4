package com.pds1.pi4.dto;

import java.io.Serializable;

import com.pds1.pi4.entidades.Usuario;

public class InserirUsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String setor;
	private String senha;
	
	public InserirUsuarioDTO() {
		
	}

	public InserirUsuarioDTO(Long id, String nome, String email, String setor, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.setor = setor;
		this.senha = senha;
	}
	
	public InserirUsuarioDTO(Usuario objUs) {
		this.id = objUs.getId();
		this.nome = objUs.getNome();
		this.email = objUs.getEmail();
		this.setor = objUs.getSetor();
		this.senha = objUs.getSenha();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario toEntity() {
		return new Usuario(id, nome, email, setor, senha);
	}
}
