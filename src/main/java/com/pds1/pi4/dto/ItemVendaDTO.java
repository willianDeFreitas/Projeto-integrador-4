package com.pds1.pi4.dto;

import com.pds1.pi4.entidades.ItemVenda;
import com.pds1.pi4.entidades.Produto;
import com.pds1.pi4.entidades.Venda;

public class ItemVendaDTO {
	
	private Long id;
	private Double qtdItemV;
	private Double valorItemV;
	private Long produtoId;
	private Long vendaId;
	private String conferido;
	private String produto;
	private String cliente;
	
	public ItemVendaDTO() {}

	public ItemVendaDTO(Long id, Double qtdItemV, Double valorItemV, Long produtoId, Long vendaId, String conferido) {
		super();
		this.qtdItemV = qtdItemV;
		this.valorItemV = valorItemV;
		this.produtoId = produtoId;
		this.vendaId = vendaId;
		this.conferido = conferido;
	}
	
	public ItemVendaDTO(Long id, Double qtdItemV, Double valorItemV, Long produtoId, Long vendaId, String conferido, String produto, String cliente) {
		this.qtdItemV = qtdItemV;
		this.valorItemV = valorItemV;
		this.produtoId = produtoId;
		this.vendaId = vendaId;
		this.conferido = conferido;
		this.produto = produto;
		this.cliente = cliente;
	}
	
	public ItemVendaDTO(ItemVenda objVend) {
		if (objVend.getProduto()==null) {
			throw new IllegalArgumentException("O produto é nulo");
		}
		
		this.id = objVend.getId();
		this.qtdItemV = objVend.getQtdItemV();
		this.valorItemV = objVend.getPrecoItemV();
		this.produtoId = objVend.getProduto().getId();
		this.vendaId = objVend.getVenda().getId();
		this.conferido = objVend.getConferido();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQtdItemV() {
		return qtdItemV;
	}

	public void setQtdItemV(Double qtdItemV) {
		this.qtdItemV = qtdItemV;
	}

	public Double getValorItemV() {
		return valorItemV;
	}

	public void setValorItemV(Double valorItemV) {
		this.valorItemV = valorItemV;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Long getVendaId() {
		return vendaId;
	}

	public void setVendaId(Long vendaId) {
		this.vendaId = vendaId;
	}

	public String getConferido() {
		return conferido;
	}

	public void setConferido(String conferido) {
		this.conferido = conferido;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public ItemVenda toEntity() {
		Venda venda = new Venda(vendaId, null, null, null);
		Produto produto = new Produto(produtoId, null, null, 0.0, 0.0, null, null);
		return new ItemVenda(null, venda, produto, qtdItemV, valorItemV, conferido);
	}
}
