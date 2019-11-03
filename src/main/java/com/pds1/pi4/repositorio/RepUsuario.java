package com.pds1.pi4.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pds1.pi4.entidades.Usuario;

public interface RepUsuario extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);
}
