package com.pds1.pi4.entidades;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.pds1.pi4.entidades.enums.CompraStatus;

@Entity
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private Instant dataReg;
	
	private Integer compraStatus;

	@OneToMany(mappedBy = "id.compra")
	private Set<ItemCompra> itemsCompra = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;
	
	public Compra() {

	}

	public Compra(Long id, Instant dataReg, CompraStatus compraStatus, Fornecedor fornecedor) {
		super();
		this.id = id;
		this.dataReg = dataReg;
		setCompraStatus(compraStatus);
		this.fornecedor = fornecedor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDataReg() {
		return dataReg;
	}

	public void setDataReg(Instant dataReg) {
		this.dataReg = dataReg;
	}

	
	public CompraStatus getCompraStatus() {
		return CompraStatus.valueOf(compraStatus);
	}

	public void setCompraStatus(CompraStatus compraStatus) {
		if(compraStatus != null) {
		this.compraStatus = compraStatus.getCode();
		
		}
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Set<ItemCompra> getIntemsCompra(){
		return itemsCompra;
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
		Compra other = (Compra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
