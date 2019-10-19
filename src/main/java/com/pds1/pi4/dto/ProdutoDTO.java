package com.pds1.pi4.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pds1.pi4.entidades.Categoria;
import com.pds1.pi4.entidades.Produto;

public class ProdutoDTO {

	private Long id;
	private String nome;
	private String vol;
	private double qtd;
	private double preco;
	private Long categoriaId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'z'", timezone = "GMT")
	private Instant datareg;

	public ProdutoDTO(Long id, String nome, String vol, double qtd, double preco, Instant datareg, Long categoriaId) {
		super();
		this.id = id;
		this.nome = nome;
		this.vol = vol;
		this.qtd = qtd;
		this.preco = preco;
		this.datareg = datareg;
		this.categoriaId = categoriaId;
	}

	public ProdutoDTO(Produto objPro) {
		this.id = objPro.getId();
		this.nome = objPro.getNome();
		this.vol = objPro.getVol();
		this.qtd = objPro.getQtd();
		this.preco = objPro.getPreco();
		this.datareg = objPro.getDatareg();
		this.categoriaId = objPro.getCategoria().getId();
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

	public String getVol() {
		return vol;
	}

	public void setVol(String vol) {
		this.vol = vol;
	}

	public double getQtd() {
		return qtd;
	}

	public void setQtd(double qtd) {
		this.qtd = qtd;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Instant getDatareg() {
		return datareg;
	}

	public void setDatareg(Instant datareg) {
		this.datareg = datareg;
	}
	
	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Produto toEntity() {
		Categoria cat = new Categoria(categoriaId, null, null);
		return new Produto(id, nome, vol, qtd, preco, datareg, cat);
	}

	
}
