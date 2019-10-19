package com.pds1.pi4.dto;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pds1.pi4.entidades.Categoria;


public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private String nome ;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'z'", timezone = "GMT")
	private Instant dataReg;
	
		
	public CategoriaDTO() {
		
	}

	public CategoriaDTO(Long id, String nome, Instant dataReg) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataReg = dataReg;
	}

	public CategoriaDTO(Categoria entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
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

	
	public Instant getDataReg() {
		return dataReg;
	}

	public void setDataReg(Instant dataReg) {
		this.dataReg = dataReg;
	}

	public Categoria toEntity() {
		return new Categoria(id, nome, dataReg);
				
	}
	
}
