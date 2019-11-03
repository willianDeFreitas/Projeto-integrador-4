package com.pds1.pi4.servicos.validacao;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pds1.pi4.dto.InserirUsuarioDTO;
import com.pds1.pi4.entidades.Usuario;
import com.pds1.pi4.recursos.exceptions.FieldMessage;
import com.pds1.pi4.repositorio.RepUsuario;

public class ValidarUsuarioInserir implements ConstraintValidator<InserirUsuarioValido, InserirUsuarioDTO> {
	@Autowired
	private RepUsuario repUsuario;
	
	@Override
	public void initialize(InserirUsuarioValido ann) {
	}

	@Override
	public boolean isValid(InserirUsuarioDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Usuario usuario = repUsuario.findByEmail(dto.getEmail());
		
		if(usuario != null) {
			list.add(new FieldMessage("email", "Email ja esta cadastrado" ));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}