package com.ordermanagermvc.model.entity;

import java.math.BigDecimal;

public class ItemPedido {
    private Integer quantidade;
    private BigDecimal precoUnitario;

    public ItemPedido(int quantidade, BigDecimal precoUnitario ){
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal subtotal(){
        if (this.quantidade == null || this.precoUnitario == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(this.quantidade).multiply(this.precoUnitario);
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
