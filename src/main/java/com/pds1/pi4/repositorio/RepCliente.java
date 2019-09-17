package com.pds1.pi4.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pds1.pi4.entidades.Cliente;

public interface RepCliente extends JpaRepository<Cliente, Long>{

}
