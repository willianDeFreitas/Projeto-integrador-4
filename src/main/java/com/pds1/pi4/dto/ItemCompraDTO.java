package com.pds1.pi4.dto;

import java.io.Serializable;

import javax.persistence.EmbeddedId;

import com.pds1.pi4.entidades.Compra;
import com.pds1.pi4.entidades.ItemCompra;
import com.pds1.pi4.entidades.Produto;
import com.pds1.pi4.entidades.pk.ItemCompraPk;

public class ItemCompraDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
			
		private Integer qtd;
		private double preco;
		private Long produtoId;
		private String produtoNome;

		public ItemCompraDTO() {

		}

		public ItemCompraDTO(Integer qtd, double preco, Long produtoId, String produtoNome) {
			super();
			this.qtd = qtd;
			this.preco = preco;
			this.produtoId = produtoId;
			this.produtoNome = produtoNome;
			
		}
		
		public ItemCompraDTO(ItemCompra objComp) {
			if (objComp.getProduto()==null) {
				throw new IllegalArgumentException("O produto e nulo");
			}
			this.qtd = objComp.getQtdItemC();
			this.preco = objComp.getPrecoItemC();
			this.produtoId = objComp.getProduto().getId();
			this.produtoNome = objComp.getProduto().getNome();
			
		}

		public Integer getQtd() {
			return qtd;
		}

		public void setQtd(Integer qtd) {
			this.qtd = qtd;
		}

		public double getPreco() {
			return preco;
		}

		public void setPreco(double preco) {
			this.preco = preco;
		}

		public Long getProdutoId() {
			return produtoId;
		}

		public void setProdutoId(Long produtoId) {
			this.produtoId = produtoId;
		}

		public String getProdutoNome() {
			return produtoNome;
		}

		public void setProdutoNome(String produtoNome) {
			this.produtoNome = produtoNome;
		}
		
}
