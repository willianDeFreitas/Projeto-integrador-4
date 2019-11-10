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
	
	public ItemVendaDTO() {}

	public ItemVendaDTO(Long id, Double qtdItemV, Double valorItemV, Long produtoId, Long vendaId) {
		super();
		this.id = id;
		this.qtdItemV = qtdItemV;
		this.valorItemV = valorItemV;
		this.produtoId = produtoId;
		this.vendaId = vendaId;
	}
	
	public ItemVendaDTO(ItemVenda entity) {
		setId(entity.getId());
		setQtdItemV(entity.getQtdItemV());
		setValorItemV(entity.getPrecoItemV());
		setProdutoId(entity.getProduto().getId());
		setVendaId(entity.getVenda().getId());
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

	public ItemVenda toEntity() {
		Venda venda = new Venda(vendaId, null, null, null);
		Produto produto = new Produto(produtoId, null, null, 0.0, 0.0, null, null);
		return new ItemVenda(null, venda, produto, qtdItemV, valorItemV);
	}
}
