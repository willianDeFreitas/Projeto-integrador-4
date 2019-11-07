package com.pds1.pi4.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pds1.pi4.entidades.Cliente;
import com.pds1.pi4.entidades.Usuario;
import com.pds1.pi4.entidades.Venda;

public class VendaItemVendaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant data;
	private float totalV;
	private Long usuarioId;
	private String usuarioNome;
	private Long clienteId;
	private String clienteNome;
	private String clienteCpf;
	private String clienteEmail;
	
	private List<ItemVendaDTO> itemVenda = new ArrayList<>();
	
	public VendaItemVendaDTO() {
	}

	public VendaItemVendaDTO(Instant data, float totalV, Long usuarioId, String usuarioNome, Long clienteId,
			String clienteNome, String clienteCpf, String clienteEmail, List<ItemVendaDTO> itemVenda) {
		super();
		this.data = data;
		this.totalV = totalV;
		this.usuarioId = usuarioId;
		this.usuarioNome = usuarioNome;
		this.clienteId = clienteId;
		this.clienteNome = clienteNome;
		this.clienteCpf = clienteCpf;
		this.clienteEmail = clienteEmail;
		this.itemVenda = itemVenda;
	}
	
	public VendaItemVendaDTO(Venda entity) {
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

	public float getTotalV() {
		return totalV;
	}

	public void setTotalV(float totalV) {
		this.totalV = totalV;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getClienteCpf() {
		return clienteCpf;
	}

	public void setClienteCpf(String clienteCpf) {
		this.clienteCpf = clienteCpf;
	}

	public String getClienteEmail() {
		return clienteEmail;
	}

	public void setClienteEmail(String clienteEmail) {
		this.clienteEmail = clienteEmail;
	}

	public List<ItemVendaDTO> getItemVenda() {
		return itemVenda;
	}
	
	public Venda toEntity() {
		Cliente cliente = new Cliente(clienteId, clienteNome, clienteCpf, null, null, null);
		Usuario usuario = new Usuario(usuarioId, usuarioNome, null, null);
		return new Venda(null, data, totalV, cliente, usuario);
	}

}
