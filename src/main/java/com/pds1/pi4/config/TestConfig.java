package com.pds1.pi4.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pds1.pi4.entidades.Categoria;
import com.pds1.pi4.entidades.Cliente;
import com.pds1.pi4.entidades.Compra;
import com.pds1.pi4.entidades.Fornecedor;
import com.pds1.pi4.entidades.Funcao;
import com.pds1.pi4.entidades.ItemCompra;
import com.pds1.pi4.entidades.ItemVenda;
import com.pds1.pi4.entidades.Produto;
import com.pds1.pi4.entidades.Usuario;
import com.pds1.pi4.entidades.Venda;
import com.pds1.pi4.entidades.enums.CompraStatus;
import com.pds1.pi4.repositorio.RepCategoria;
import com.pds1.pi4.repositorio.RepCliente;
import com.pds1.pi4.repositorio.RepCompra;
import com.pds1.pi4.repositorio.RepFornecedor;
import com.pds1.pi4.repositorio.RepFuncao;
import com.pds1.pi4.repositorio.RepItemCompra;
import com.pds1.pi4.repositorio.RepItemVenda;
import com.pds1.pi4.repositorio.RepProduto;
import com.pds1.pi4.repositorio.RepUsuario;
import com.pds1.pi4.repositorio.RepVenda;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder senhaEncode;
	
	@Autowired
	private RepUsuario repUsuario;
	
	@Autowired
	private RepCliente repCliente;
	
	@Autowired
	private RepFornecedor repFornecedor;

	@Autowired
	private RepCompra repCompra;

	@Autowired
	private RepCategoria repCategoria;

	@Autowired
	private RepProduto repProduto;
	
	@Autowired
	private RepItemCompra repItemCompra;

	@Autowired
	private RepVenda repVenda;
	
	@Autowired
	private RepItemVenda repItemVenda;
	
	@Autowired
	private RepFuncao repFuncao;
	
	
	
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Areia", Instant.parse("2019-06-20T19:53:07Z"));
		Categoria cat2 = new Categoria(null, "Brita", Instant.parse("2019-06-20T19:53:07Z"));
		
		Produto p1 = new Produto(null, "Areia media", "30m³", 100.00, 30.00, Instant.parse("2019-06-20T19:53:07Z"), cat1);
		Produto p2 = new Produto(null, "Areia fina", "30m³", 100.00, 30.00, Instant.parse("2019-06-20T19:53:07Z"), cat1);
		Produto p3 = new Produto(null, "Brita 1", "30m³", 100.00, 30.00, Instant.parse("2019-06-20T19:53:07Z"), cat2);
		Produto p4 = new Produto(null, "Brita 0", "30m³", 100.00, 30.00, Instant.parse("2019-06-20T19:53:07Z"), cat2);
		
		
		repCategoria.saveAll(Arrays.asList(cat1, cat2));
		repProduto.saveAll(Arrays.asList(p1, p2, p3, p4));
		
		Usuario u1 = new Usuario(null, "Pedro Adm", "pedro@gmail.com", senhaEncode.encode("123456"));
		Usuario u2 = new Usuario(null, "Daniela Sec", "daniela@gmail.com", senhaEncode.encode("123456"));
		Usuario u3 = new Usuario(null, "Mateus Est", "mateus@gmail.com", senhaEncode.encode("123456"));

		repUsuario.saveAll(Arrays.asList(u1, u2, u3));
		
		Funcao f1 = new Funcao(null, "ROLE_ADMIN");
		Funcao f2 = new Funcao(null, "ROLE_SECRET");
		Funcao f3 = new Funcao(null, "ROLE_ESTOQ");
		
		repFuncao.saveAll(Arrays.asList(f1, f2, f3));
		
		u1.getFuncoes().add(f1);
		u1.getFuncoes().add(f2);
		u1.getFuncoes().add(f3);
		u2.getFuncoes().add(f2);
		u3.getFuncoes().add(f3);
		
		repUsuario.saveAll(Arrays.asList(u1, u2, u3));
		
		Fornecedor for1 = new Fornecedor(null, "Areia total", "11111111111", "rua mangue, 201, jb", "areiatotal@hotmail.com", "11111111");
		
		repFornecedor.saveAll(Arrays.asList(for1));
		
		Compra c1 = new Compra(null, Instant.parse("2019-06-20T19:53:07Z"), CompraStatus.PAGO, u1, for1);
		Compra c2 = new Compra(null, Instant.parse("2019-07-21T03:42:10Z"), CompraStatus.PAGTO_PENDENTE, u2, for1);
		Compra c3 = new Compra(null, Instant.parse("2019-07-22T15:21:22Z"), CompraStatus.PAGTO_PENDENTE, u1, for1);

		repCompra.saveAll(Arrays.asList(c1, c2, c3));
		
		ItemCompra ic1 = new ItemCompra(c1,p1, 2, p1.getPreco());
		ItemCompra ic2 = new ItemCompra(c1,p3, 1, p1.getPreco());
		ItemCompra ic3 = new ItemCompra(c2,p3, 2, p1.getPreco());
		ItemCompra ic4 = new ItemCompra(c1,p4, 2, p1.getPreco());

		repItemCompra.saveAll(Arrays.asList(ic1, ic2, ic3, ic4));
		
		Cliente cli1 = new Cliente(null, "Mario", "00011122233", "rua E, 100, bairro ae", "mario@hotmail.com", "34999887744");
		
		Venda v1 = new Venda(null, Instant.parse("2019-06-20T19:53:07Z"), 100, cli1, u1);
		
		repCliente.saveAll(Arrays.asList(cli1));
		repVenda.saveAll(Arrays.asList(v1));
		
		ItemVenda itv = new ItemVenda(1L, p1.getId(), v1.getId(), 100.0, 100.0, false);
		
		repItemVenda.saveAll(Arrays.asList(itv));
		
	}
}
