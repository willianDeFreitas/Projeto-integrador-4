package com.pds1.pi4.servicos.validacao;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ValidarUsuarioAtualizar.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AtualizarUsuarioValido {
	
	String message()default "Validation error";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload()default{};
}