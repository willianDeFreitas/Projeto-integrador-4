package com.pds1.pi4.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	private Integer qtdItemC;
	private double precoItemC;
	private String conferido;

	public ItemCompra() {}

	public ItemCompra(Long id, Compra compra, Produto produto, Integer qtdItemC, double precoItemC, String conferido) {
		super();
		this.id = id;
		this.compra = compra;
		this.produto = produto;
		this.qtdItemC = qtdItemC;
		this.precoItemC = precoItemC;
		this.conferido = conferido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
		ItemCompra other = (ItemCompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
