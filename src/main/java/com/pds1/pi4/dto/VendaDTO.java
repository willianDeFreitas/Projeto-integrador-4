package com.pds1.pi4.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pds1.pi4.entidades.Cliente;
import com.pds1.pi4.entidades.Venda;

public class VendaDTO {

private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant data;
	private Double totalV;
	private Long clienteId;
	
	private List<ItemVendaDTO> itensVenda = new ArrayList<>();
	
	public VendaDTO() {}

	public VendaDTO(Long id, Instant data, Double totalV, Long clienteId, List<ItemVendaDTO> itensVenda) {
		super();
		this.id = id;
		this.data = data;
		this.totalV = totalV;
		this.clienteId = clienteId;
		this.itensVenda = itensVenda;
	}
	
	public VendaDTO(Venda objVend) {
		if(objVend.getCliente() == null) {
			throw new IllegalArgumentException("Error instiating vendaDTO: compra was null");
		}
		
		this.id = objVend.getId();
		this.data = objVend.getDate();
		this.totalV = objVend.getTotalV();
		this.clienteId = objVend.getCliente().getId();
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

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Double getTotalV() {
		return totalV;
	}

	public void setTotalV(Double totalV) {
		this.totalV = totalV;
	}
	
	public List<ItemVendaDTO> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVendaDTO> itensVenda) {
		this.itensVenda = itensVenda;
	}
	
	public Venda toEntity() {
		Cliente cliente = new Cliente(clienteId, null, null, null, null, null);
		return new Venda(id, data, totalV, cliente);
	}
}