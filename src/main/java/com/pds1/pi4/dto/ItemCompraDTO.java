package com.pds1.pi4.dto;

import java.io.Serializable;

import com.pds1.pi4.entidades.Compra;
import com.pds1.pi4.entidades.ItemCompra;
import com.pds1.pi4.entidades.Produto;

public class ItemCompraDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
		private Long id;	
		private Integer qtdItemC;
		private Double valorItemC;
		private Long produtoId;
		private Long compraId;
		private String conferido;

		public ItemCompraDTO() {

		}

		public ItemCompraDTO(Long id, Integer qtdItemC, double valorItemC, Long produtoId, Long compraId, String conferido) {
			super();
			this.qtdItemC = qtdItemC;
			this.valorItemC = valorItemC;
			this.produtoId = produtoId;
			this.compraId = compraId;
			this.conferido = conferido;			
		}
		
		public ItemCompraDTO(ItemCompra objComp) {
			if (objComp.getProduto()==null) {
				throw new IllegalArgumentException("O produto é nulo");
			}
			
			this.id = objComp.getId();
			this.qtdItemC = objComp.getQtdItemC();
			this.valorItemC = objComp.getPrecoItemC();
			this.produtoId = objComp.getProduto().getId();
			this.compraId = objComp.getCompra().getId();
			this.conferido = objComp.getConferido();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Integer getQtdItemC() {
			return qtdItemC;
		}

		public void setQtdItemC(Integer qtdItemC) {
			this.qtdItemC = qtdItemC;
		}

		public Double getValorItemC() {
			return valorItemC;
		}

		public void setValorItemC(Double valorItemC) {
			this.valorItemC = valorItemC;
		}

		public Long getProdutoId() {
			return produtoId;
		}

		public void setProdutoId(Long produtoId) {
			this.produtoId = produtoId;
		}

		public Long getCompraId() {
			return compraId;
		}

		public void setCompraId(Long compraId) {
			this.compraId = compraId;
		}

		public String getConferido() {
			return conferido;
		}

		public void setConferido(String conferido) {
			this.conferido = conferido;
		}

		public ItemCompra toEntity() {
			Compra compra = new Compra(compraId, null, null, null);
			Produto produto = new Produto(produtoId, null, null, 0.0, 0.0, null, null);
			return new ItemCompra(id, compra, produto, qtdItemC, valorItemC, conferido);
		}
		
}
