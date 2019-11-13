package com.pds1.pi4.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pds1.pi4.entidades.Compra;
import com.pds1.pi4.entidades.Fornecedor;
import com.pds1.pi4.entidades.enums.CompraStatus;

public class CompraDTO {

	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dataReg;
	private Double totalCompra; 
	private Long fornecedorId;
	
	private List<ItemCompraDTO> itensCompra = new ArrayList<>();
	
	public CompraDTO() {}

	public CompraDTO(Long id, Instant dataReg, Double totalCompra, Long fornecedorId, String fornecedorNome, List<ItemCompraDTO> itensCompra) {
		super();
		this.id= id;
		this.dataReg = dataReg;
		this.totalCompra = totalCompra;
		this.fornecedorId = fornecedorId;
		this.itensCompra= itensCompra;
	}
	
	public CompraDTO(Compra objComp) {
		if(objComp.getFornecedor() == null) {
			throw new IllegalArgumentException("Error instiating compraDTO: compra was null");
		}
		this.id= objComp.getId();
		this.dataReg = objComp.getDataReg();
		this.totalCompra = objComp.getTotalCompra();
		this.fornecedorId = objComp.getFornecedor().getId();
		
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

	
	public Double getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(Double totalCompra) {
		this.totalCompra = totalCompra;
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
		return new Compra(id, dataReg, totalCompra, fornecedor);
	}
}
