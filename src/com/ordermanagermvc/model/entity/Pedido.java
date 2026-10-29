package com.ordermanagermvc.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {
	private final String uuId;
	private final Cliente cliente;
	private List<ItemPedido> items = new ArrayList<>();


	public Pedido(String uuId, Cliente cliente) {
		this.uuId = uuId;
		this.cliente = cliente;

		if (cliente != null && !cliente.getPedidos().contains(this)) {
			cliente.getPedidos().add(this);
		}
	}

	public String getUuId() { return uuId; }
	public Cliente getCliente() { return cliente; }


	public void adicionarItem(ItemPedido item){
		items.add(item);
	}

	public BigDecimal valorTotal(){
		BigDecimal precoTotal = BigDecimal.ZERO;
		for (ItemPedido item: items){
			precoTotal = precoTotal.add(item.subtotal());

		}
		return  precoTotal;
	}



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
		return "Pedido #" + uuId + " | Cliente: " + cliente.getNome() +
				" | Qtd Itens: " + items.size() + " | Total: R$ " + valorTotal();
	}
}