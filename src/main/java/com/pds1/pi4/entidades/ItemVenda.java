package com.pds1.pi4.entidades;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


import com.pds1.pi4.entidades.pk.ItemVendaPk;

@Entity
public class ItemVenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemVendaPk id = new ItemVendaPk();
	
	private Long idItemVenda;
	private double qtdItemV;
	private double precoItemV;
	
	public ItemVenda() {
		
	}

	public ItemVenda(Long idItemVenda, Venda venda, Produto produto, double qtdItemV, double precoItemV) {
		super();
		id.setVenda(venda);
		id.setProduto(produto);
		this.idItemVenda = idItemVenda;
		this.qtdItemV = qtdItemV;
		this.precoItemV = precoItemV;
	}

	
	public Venda getVenda() {
		return id.getVenda();
	}
	public void setVenda(Venda venda) {
		id.setVenda(venda);
	}
	public Produto getProduto() {
		return id.getProduto();
		
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
	public double getQtdItemV() {
		return qtdItemV;
	}
	
	public void setQtdItemV(Integer qtdItemV) {
		this.qtdItemV = qtdItemV;
	}

	public double getPrecoItemV() {
		return precoItemV;
	}

	public void setPrecoItemV(float precoItemV) {
		this.precoItemV = precoItemV;
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
		ItemVenda other = (ItemVenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getIdItemVenda() {
		return idItemVenda;
	}

	public void setIdItemVenda(Long idItemVenda) {
		this.idItemVenda = idItemVenda;
	}
	
}