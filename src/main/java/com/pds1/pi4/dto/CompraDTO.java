package com.pds1.pi4.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pds1.pi4.entidades.Compra;
import com.pds1.pi4.entidades.Fornecedor;

public class CompraDTO {

	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant data;
	private Double totalC; 
	private Long fornecedorId;
	
	private List<ItemCompraDTO> itensCompra = new ArrayList<>();
	
	public CompraDTO() {}

	public CompraDTO(Long id, Instant data, Double totalC, Long fornecedorId, List<ItemCompraDTO> itensCompra) {
		super();
		this.id= id;
		this.data = data;
		this.totalC = totalC;
		this.fornecedorId = fornecedorId;
		this.itensCompra= itensCompra;
	}
	
	public CompraDTO(Compra objComp) {
		if(objComp.getFornecedor() == null) {
			throw new IllegalArgumentException("Error instiating compraDTO: compra was null");
		}
		
		this.id= objComp.getId();
		this.data = objComp.getData();
		this.totalC = objComp.getTotalC();
		this.fornecedorId = objComp.getFornecedor().getId();
		
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	
	public Double getTotalC() {
		return totalC;
	}

	public void setTotalC(Double totalC) {
		this.totalC = totalC;
	}

	public Long getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(Long fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public List<ItemCompraDTO> getItensCompra() {
		return itensCompra;
	}

	public void setItensCompra(List<ItemCompraDTO> itensCompra) {
		this.itensCompra = itensCompra;
	}

	public Compra toEntity() {
		Fornecedor fornecedor =new Fornecedor(fornecedorId,null, null, null, null, null);
		return new Compra(id, data, totalC, fornecedor);
	}
}
