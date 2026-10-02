package com.ordermanagermvc.model.entity;

public  class Pedido {
		private String uuId;
		private String descricao;
		private double valor;
		
		public Pedido (String uuId, String descricao, double valor) {
			this.uuId = uuId;
			this.descricao = descricao;
			this.valor = valor;
			
		}
		

	    // Getters e Setters
	    public String getUuId() { return uuId; }
	    public void setUuId(String uuId) { this.uuId = uuId; }
	    public String getDescricao() { return descricao; }
	    public void setDescricao(String descricao) { this.descricao = descricao; }
	    public double getValor() { return valor; }
	    public void setValor(double valor) { this.valor = valor; }

	    @Override
	    public String toString() {
	        return "Pedido #" + uuId + " - " + descricao + " (R$ " + valor + ")";
	    }
	}
		

