package com.pds1.pi4.dto;

import java.io.Serializable;

import com.pds1.pi4.entidades.Usuario;

public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String setor;
	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(Long id, String nome, String email, String setor) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.setor = setor;
	}
	
	public UsuarioDTO(Usuario objUs) {
		this.id = objUs.getId();
		this.nome = objUs.getNome();
		this.email = objUs.getEmail();
		this.setor = objUs.getSetor();
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
	
	public Usuario toEntity() {
		return new Usuario(id, nome, email, null, setor);
	}
}
