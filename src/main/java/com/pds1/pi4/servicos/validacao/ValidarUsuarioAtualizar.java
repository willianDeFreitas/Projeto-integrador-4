package com.pds1.pi4.servicos.validacao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.pds1.pi4.dto.UsuarioDTO;
import com.pds1.pi4.entidades.Usuario;
import com.pds1.pi4.recursos.exceptions.FieldMessage;
import com.pds1.pi4.repositorio.RepUsuario;

public class ValidarUsuarioAtualizar implements ConstraintValidator<AtualizarUsuarioValido, UsuarioDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private RepUsuario repUsuario;
	
	@Override
	public void initialize(AtualizarUsuarioValido ann) {
	}

	@Override
	public boolean isValid(UsuarioDTO dto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map =
		(Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Usuario usuario = repUsuario.findByEmail(dto.getEmail());
		
		if(usuario != null && !usuario.getId().equals(uriId)) {
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