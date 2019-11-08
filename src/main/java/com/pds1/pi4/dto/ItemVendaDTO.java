package com.pds1.pi4.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.pds1.pi4.entidades.ItemVenda;
import com.pds1.pi4.entidades.Venda;

public class ItemVendaDTO {
	
	private Instant data;
	private Double totalV;
	private Long clienteId;
	
	private List<ItemVenda> itensVenda = new ArrayList<>();
	
	public ItemVendaDTO() {}

	public ItemVendaDTO(Instant data, Double totalV, Long clienteId, List<ItemVenda> itensVenda) {
		super();
		this.data = data;
		this.totalV = totalV;
		this.clienteId = clienteId;
	}
	
	public ItemVendaDTO(Venda entity) {
		setData(entity.getDate());
		setTotalV(entity.getTotalV());
		setClienteId(entity.getCliente().getId());	
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public Double getTotalV() {
		return totalV;
	}

	public void setTotalV(Double totalV) {
		this.totalV = totalV;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}
	
	public Venda toEntity() {
		return new Venda(null, data, totalV, null);
	}

}
