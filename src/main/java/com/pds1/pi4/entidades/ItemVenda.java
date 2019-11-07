package com.pds1.pi4.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_itemvenda")
public class ItemVenda implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idProd;
	private Long idVenda;
	private Double qtdItemV;
	private Double precoItemV;
	private boolean conferido;
	
	@ManyToOne
	@JoinColumn(name = "venda_id")
	private Venda venda;
	
	public ItemVenda() {}

	public ItemVenda(Long id, Long idProd, Long idVenda, Double qtdItemV, Double precoItemV, boolean conferido) {
		super();
		this.id = id;
		this.idProd = idProd;
		this.idVenda = idVenda;
		this.qtdItemV = qtdItemV;
		this.precoItemV = precoItemV;
		this.conferido = conferido;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getIdProd() {
		return idProd;
	}

	public void setIdProd(long idProd) {
		this.idProd = idProd;
	}

	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(long idVenda) {
		this.idVenda = idVenda;
	}

	public Double getQtdItemV() {
		return qtdItemV;
	}

	public void setQtdItemV(Double qtdItemV) {
		this.qtdItemV = qtdItemV;
	}

	public Double getPrecoItemV() {
		return precoItemV;
	}

	public void setPrecoItemV(Double precoItemV) {
		this.precoItemV = precoItemV;
	}

	public boolean isConferido() {
		return conferido;
	}

	public void setConferido(boolean conferido) {
		this.conferido = conferido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (id != other.id)
			return false;
		return true;
	}

}
