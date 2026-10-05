package com.ordermanagermvc.model.entity;
import java.math.BigDecimal;

public  class Pedido {
		private String uuId;
		private String descricao;
		private BigDecimal valor;
		private  Cliente cliente;
		
		public Pedido (String uuId, String descricao, BigDecimal valor, Cliente cliente) {
			this.uuId = uuId;
			this.descricao = descricao;
			this.valor = valor;
			this.cliente = cliente;

			if (cliente != null && !cliente.getPedidos().contains(this)) {
				cliente.getPedidos().add(this);
			}
		}
		

	    // Getters e Setters
	    public String getUuId() { return uuId; }
	    public void setUuId(String uuId) { this.uuId = uuId; }
	    public String getDescricao() { return descricao; }
	    public void setDescricao(String descricao) { this.descricao = descricao; }
	    public BigDecimal getValor() { return valor; }
	    public void setValor(BigDecimal valor) { this.valor = valor; }
		public Cliente getCliente() { return cliente; }

	    @Override
	    public String toString() {
	        return "Pedido #" + uuId + " - " + descricao + " (R$ " + valor + ")";
	    }
	}
		

