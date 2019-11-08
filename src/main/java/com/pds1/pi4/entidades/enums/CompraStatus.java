package com.pds1.pi4.entidades.enums;

public enum CompraStatus {
	PAGTO_PENDENTE(1),
	PAGO(2),
	ENVIADO(3),
	ENTREGUE(4),
	CANCELADO(5);

	private int code;
	
	private CompraStatus(int code) {
		this.code= code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static CompraStatus valueOf(int code) {
		for(CompraStatus value : CompraStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo CompraStatus invalido");
	}
}
