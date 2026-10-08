package com.ordermanagermvc.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Pedido {
	private final String uuId;
	private String descricao;
	private BigDecimal valor;
	private final Cliente cliente;

	public Pedido(String uuId, String descricao, BigDecimal valor, Cliente cliente) {
		this.uuId = uuId;
		this.descricao = descricao;
		this.valor = valor;
		this.cliente = cliente;

		if (cliente != null && !cliente.getPedidos().contains(this)) {
			cliente.getPedidos().add(this);
		}
	}

	public String getUuId() { return uuId; }
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	public BigDecimal getValor() { return valor; }
	public void setValor(BigDecimal valor) { this.valor = valor; }
	public Cliente getCliente() { return cliente; }


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pedido pedido = (Pedido) o;
		return Objects.equals(uuId, pedido.uuId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuId);
	}


	@Override
	public String toString() {
		return "Pedido #" + uuId + " - " + descricao + " (R$ " + valor + ")";
	}
}