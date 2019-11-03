package com.pds1.pi4.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.pds1.pi4.entidades.Usuario;
import com.pds1.pi4.servicos.validacao.AtualizarUsuarioValido;

@AtualizarUsuarioValido
public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message= "O campo não pode esta vazio")
	private String nome;
	@NotEmpty(message= "O campo não pode esta vazio")
	@Email(message= "Email invalido")
	private String email;
	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(Long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public UsuarioDTO(Usuario objUs) {
		this.id = objUs.getId();
		this.nome = objUs.getNome();
		this.email = objUs.getEmail();
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

		
	public Usuario toEntity() {
		return new Usuario(id, nome, email, null);
	}
}
