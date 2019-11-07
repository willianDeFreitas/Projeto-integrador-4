package com.pds1.pi4.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pds1.pi4.entidades.Cliente;
import com.pds1.pi4.entidades.Usuario;
import com.pds1.pi4.entidades.Venda;

public class VendaDTO {

private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant data;
	private float totalV;
	private Long usuarioId;
	private String usuarioNome;
	private Long clienteId;
	private String clienteNome;
	private String clienteCpf;
	private String clienteEmail;
	
	public VendaDTO() {
		
	}

	public VendaDTO(Long id, Instant data, float totalV, Long usuarioId, String usuarioNome, Long clienteId, String clienteNome,
			String clienteCpf, String clienteEmail) {
		super();
		this.id = id;
		this.data = data;
		this.totalV = totalV;
		this.clienteId = clienteId;
	}
	
	public VendaDTO(Venda objVend) {
		this.id = objVend.getId();
		this.data = objVend.getData();
		this.totalV = objVend.getTotalV();
		this.clienteId = objVend.getIdCliente();
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
	
	public Venda toEntity() {
		return new Venda(id, data, totalV, clienteId);
	}
}
