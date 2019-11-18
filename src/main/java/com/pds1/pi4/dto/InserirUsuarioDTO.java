package com.pds1.pi4.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.pds1.pi4.entidades.Usuario;
import com.pds1.pi4.servicos.validacao.InserirUsuarioValido;

@InserirUsuarioValido
public class InserirUsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	

	@NotEmpty(message= "O campo não pode esta vazio")
	private String nome;
	@NotEmpty(message= "O campo não pode esta vazio")
	@Email(message= "Email invalido")
	private String email;
	private String senha;
	private Long funcao;
	
	public InserirUsuarioDTO() {
		
	}

	public InserirUsuarioDTO(Long id, String nome, String email, String senha, Long funcao) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.funcao = funcao;
	}
	
	public InserirUsuarioDTO(Usuario objUs) {
		this.id = objUs.getId();
		this.nome = objUs.getNome();
		this.email = objUs.getEmail();
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getFuncao() {
		return funcao;
	}

	public void setFuncao(Long funcao) {
		this.funcao = funcao;
	}
	
	public Usuario toEntity() {
		return new Usuario(id, nome, email, senha);
	}
}
