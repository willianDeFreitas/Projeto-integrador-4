package com.pds1.pi4.entidades;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


import com.pds1.pi4.entidades.pk.ItemCompraPk;

@Entity
public class ItemCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemCompraPk id= new ItemCompraPk();
	
	private Integer qtdItemC;
	private double precoItemC;

	public ItemCompra() {

	}

	public ItemCompra(Compra compra, Produto produto, Integer qtdItemC, double precoItemC) {
		super();
		id.setCompra(compra);
		id.setProduto(produto);
		this.qtdItemC = qtdItemC;
		this.precoItemC = precoItemC;
	}
	
	
	public Compra getCompra() {
		return id.getCompra();
	}
	
	public void setCompra(Compra compra) {
		id.setCompra(compra);
	}
	
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
	public Integer getQtdItemC() {
		return qtdItemC;
	}

	public void setQtdItemC(Integer qtdItemC) {
		this.qtdItemC = qtdItemC;
	}

	public double getPrecoItemC() {
		return precoItemC;
	}

	public void setPrecoItemC(double precoItemC) {
		this.precoItemC = precoItemC;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCompra other = (ItemCompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
