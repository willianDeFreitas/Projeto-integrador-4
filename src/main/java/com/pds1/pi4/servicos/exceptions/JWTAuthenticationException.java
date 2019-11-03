package com.pds1.pi4.servicos.exceptions;

public class JWTAuthenticationException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JWTAuthenticationException(String msg) {
		super(msg);
	}
}
