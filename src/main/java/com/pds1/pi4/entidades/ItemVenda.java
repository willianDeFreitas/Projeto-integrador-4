package com.pds1.pi4.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemVenda implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "venda_id")
	private Venda venda;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	private double qtdItemV;
	private double precoItemV;
	private String conferido;
	
	public ItemVenda() {}

	public ItemVenda(Long id, Venda venda, Produto produto, double qtdItemV, double precoItemV, String conferido) {
		super();
		this.id = id;
		this.venda = venda;
		this.produto = produto;
		this.qtdItemV = qtdItemV;
		this.precoItemV = precoItemV;
		this.conferido = conferido;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public String getConferido() {
		return conferido;
	}

	public void setConferido(String conferido) {
		this.conferido = conferido;
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
}