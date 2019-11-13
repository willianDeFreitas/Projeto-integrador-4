package com.pds1.pi4.entidades;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant date;
	private Double totalV;
	
	@ManyToOne
	@JoinColumn(name= "cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "venda")
	private List<ItemVenda> itemsVenda = new ArrayList<>();
	
	public Venda() {
		
	}

	public Venda(Long id, Instant date, Double totalV, Cliente cliente) {
		super();
		this.id = id;
		this.date = date;
		this.totalV = totalV;
		this.cliente= cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public Double getTotalV() {
		return totalV;
	}

	public void setTotalV(Double totalV) {
		this.totalV = totalV;
	}

	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemVenda> getItemsVenda() {
		return itemsVenda;
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
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}