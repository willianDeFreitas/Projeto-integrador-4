package com.pds1.pi4.dto;

import java.io.Serializable;

public class ItemVendaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int idProd;
	private int idVenda;
	private float qtdItemVenda;
	
	public ItemVendaDTO() {}	
	
	public ItemVendaDTO(int id, int idProd, int idVenda, float qtdItemVenda, float precoItemVenda) {
		super();
		this.id = id;
		this.idProd = idProd;
		this.idVenda = idVenda;
		this.qtdItemVenda = qtdItemVenda;
		this.precoItemVenda = precoItemVenda;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdProd() {
		return idProd;
	}
	public void setIdProd(int idProd) {
		this.idProd = idProd;
	}
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public float getQtdItemVenda() {
		return qtdItemVenda;
	}
	public void setQtdItemVenda(float qtdItemVenda) {
		this.qtdItemVenda = qtdItemVenda;
	}
	public float getPrecoItemVenda() {
		return precoItemVenda;
	}
	public void setPrecoItemVenda(float precoItemVenda) {
		this.precoItemVenda = precoItemVenda;
	}
	private float precoItemVenda;

}
