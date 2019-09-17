package com.pds1.pi4.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pds1.pi4.entidades.Produto;

public interface RepProduto extends JpaRepository<Produto, Long>{

}
